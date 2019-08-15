package com.jingsong.patient.utils

import com.jingsong.patient.BuildConfig
import com.jingsong.ivmd.iter.ApiInter
import com.jingsong.ivmd.iter.DownloadListener
import com.jingsong.ivmd.utils.Utils.logs
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.*
import java.util.concurrent.Executors


/**
 * @author jingsong.chen, QQ:77132995, email:kazeik@163.com
 * 2019-08-05 09:17
 * 类说明:
 */
class DownloadUtil {
    fun download(url: String, path: String, downloadListener: DownloadListener) {

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.URL)
                //通过线程池获取一个线程，指定callback在子线程中运行。
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .build()

        val service = retrofit.create(ApiInter::class.java)

        val call = service.download(url)
        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                downloadListener.onFail("网络错误～")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                //将Response写入到从磁盘中，详见下面分析
//                //注意，这个方法是运行在子线程中的
                writeResponseToDisk(path, response, downloadListener)
            }
        })
    }

    private fun writeResponseToDisk(path: String, response: Response<ResponseBody>, downloadListener: DownloadListener) {
        //从response获取输入流以及总大小
        writeFileFromIS(File(path), response.body()!!.byteStream(), response.body()!!.contentLength(), downloadListener)
    }

    private val sBufferSize = 8192

    //将输入流写入文件
    private fun writeFileFromIS(file: File, input: InputStream, totalLength: Long, downloadListener: DownloadListener) {
        //开始下载
        downloadListener.onStart()

        //创建文件
        if (!file.exists()) {
            if (!file.parentFile.exists())
                file.parentFile.mkdir()
            try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
                downloadListener.onFail("createNewFile IOException")
            }
        }

        var os: OutputStream? = null
        var currentLength: Long = 0
        try {
            os = BufferedOutputStream(FileOutputStream(file))
            val data = ByteArray(sBufferSize)
            var len: Int = 0
//            while ({len = `is`.read(data, 0, sBufferSize)) != -1}()) {
            while ({ len = input.read(data, 0, sBufferSize);len != -1 }()) {
                os.write(data, 0, len)
                currentLength += len.toLong()
                //计算当前下载进度
                downloadListener.onProgress((100 * currentLength / totalLength).toInt())
            }
            logs("tag","下载完成= ${file.absolutePath}")
            //下载完成，并返回保存的文件路径
            downloadListener.onFinish(file.absolutePath)
        } catch (e: IOException) {
            e.printStackTrace()
            downloadListener.onFail("IOException")
        } finally {
            try {
                input.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            try {
                if (os != null) {
                    os.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}