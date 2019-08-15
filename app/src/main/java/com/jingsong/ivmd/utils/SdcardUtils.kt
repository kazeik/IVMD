package com.jingsong.patient.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.ActivityManager.MemoryInfo
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.text.format.Formatter
import java.io.*
import java.nio.charset.Charset


//import org.apache.http.util.EncodingUtils;

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
class SdcardUtils {

    /**
     * 获取SD路径
     *
     * @return /sdcard
     */
    // 判断sd卡是否存在
    // 获取跟目录
    val sdPath: String
        get() {
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                val sdDir = Environment.getExternalStorageDirectory()
                return sdDir.path
            }
            return "/mnt/sdcard"
        }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    val sdCardPath: String
        get() = Environment.getExternalStorageDirectory().toString() + "/"

    /**
     * 检查SD卡是否插好
     */
    fun SDCardIsOk(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    /**
     * 创建文件夹
     *
     * @param dirName
     */
    fun createDir(dirName: String) {
        val destDir = File(dirName)
        if (!destDir.exists()) {
            destDir.mkdirs()
        }
    }


    /**
     * 在SD卡上创建文件
     *
     * @param fileName
     * @return
     */
    fun creatSDFile(fileName: String): File {
        val file = File(sdCardPath + fileName)
        try {
            file.createNewFile()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return file
    }

    /**
     * 在SD卡上创建目录
     *
     * @param dirName
     * @return
     */
    fun createSDDir(dirName: String): File {
        val dir = File(sdCardPath + dirName)
        dir.mkdir()
        return dir
    }

    /**
     * 检查SD卡上的文件夹是否存在
     *
     * @param fileName
     * @return
     */
    fun isFileExist(fileName: String): Boolean {
        val file = File(sdCardPath + fileName)
        return file.exists()
    }

    /**
     * 判断文件是否存在
     *
     * @param name 文件名
     * @return
     */
    fun fileExist(name: String): Boolean {
        val f = File(sdCardPath + name)
        return f.exists()
    }


    /**
     * 写文件到SD卡
     *
     * @param fileName 文件名
     * @param message  文件内容
     * @author ck
     * @date 2013-1-10 下午04:35:32
     */
    fun writeFileSdcard(fileName: String, message: String) {
        try {
            val fout = FileOutputStream(fileName)
            val bytes = message.toByteArray()
            fout.write(bytes)
            fout.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 删除文件
     *
     * @param path
     */
    fun deleteFile(path: String) {
        val file = File(path)
        file.delete()
    }

    // 获得系统可用内存信息
    fun getSystemAvaialbeMemorySize(ct: Context): String {
        // 获得ActivityManager服务的对象
        val mActivityManager = ct
                .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        // 获得MemoryInfo对象
        val memoryInfo = MemoryInfo()
        // 获得系统可用内存，保存在MemoryInfo对象上
        mActivityManager.getMemoryInfo(memoryInfo)
        val memSize = memoryInfo.availMem

        // 字符类型转换
        return formateFileSize(memSize, ct)
    }

    // 调用系统函数，字符串转换 long -String KB/MB
    private fun formateFileSize(size: Long, ct: Context): String {
        return Formatter.formatFileSize(ct, size)
    }

    /**
     * 获取内存卡容量大小
     *
     * @param path
     * @return
     */
    fun getRoomSize(path: String): Long {
        val file = File(path)
        return file.length()
    }

    fun uriToFile(cont: Activity, uri: Uri): File {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val actualimagecursor = cont.managedQuery(uri, proj, null, null, null)
        val actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        actualimagecursor.moveToFirst()
        val img_path = actualimagecursor.getString(actual_image_column_index)
        actualimagecursor.close()
        return File(img_path)
    }

    fun getCacheFile(imageUri: String): File? {
        var cacheFile: File? = null
        try {
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                val sdCardDir = Environment.getExternalStorageDirectory()
                val fileName = getFileName(imageUri)
                val dir = File(sdCardDir.canonicalPath + "cache")
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                cacheFile = File(dir, fileName)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return cacheFile
    }

    fun getFileName(path: String): String {
        val index = path.lastIndexOf("/")
        return path.substring(index + 1)
    }

    fun bytes2Bimap(b: ByteArray): Bitmap? {
        return if (b.isNotEmpty()) BitmapFactory.decodeByteArray(b, 0, b.size) else null
    }

    fun bitmap2Bytes(bm: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }

    /**
     * 按行读取txt
     * 解析输入流，返回txt中的字符串
     * @param input
     * @return
     */
    fun readTextFromSDcard(input: InputStream): String {
        val baos = ByteArrayOutputStream()
        var i: Int
        while ((input.read()).also { i = it } != -1) {
            baos.write(i)
        }
        return baos.toString()
    }

}
