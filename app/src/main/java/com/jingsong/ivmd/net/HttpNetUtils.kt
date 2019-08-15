@file:Suppress("DEPRECATION")

package com.jingsong.ivmd.net

import android.text.TextUtils
import com.google.gson.Gson
import com.jingsong.ivmd.BuildConfig
import com.jingsong.ivmd.iter.ApiInter
import com.jingsong.patient.utils.ApiUtils
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


@Suppress("DEPRECATION", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
/**
 * @author hope.chen, QQ:77132995, email:kazeik@163.com
 * 2017 04 27 11:28
 * 类说明:
 */
class HttpNetUtils : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val tagUrl = chain.request().url().encodedPath()
        val time = timeHash[tagUrl]
        val request = chain.request().newBuilder()
            .header(
                "Authorization", "Bearer ${if (TextUtils.isEmpty(ApiUtils.token)) {
                    ""
                } else {
                    ApiUtils.token
                }}"
            )
            .header("X-Request-Timestamp", "$time")
            .build()
        timeHash.remove(tagUrl)
        return chain.proceed(request)
    }

    private val okClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(this)
            .retryOnConnectionFailure(true)
            .connectTimeout(time, TimeUnit.SECONDS)
            .readTimeout(time, TimeUnit.SECONDS)
            .writeTimeout(time, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.LOG_DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                )
            )
            .build()
    }
    private val time: Long = 30
    private val retrofit: Retrofit? by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.URL)
            .client(okClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    /**
     * 计算请求头
     * @param method String 请求方式
     * @param url String? 请求url
     * @param token String? 登录返回的token
     * @return String
     */
//    fun getParamsHeader(method: String, url: String? = null): String {
//        return getParamsHeader(method, null, url)
//    }

//    fun getUploadImageParts(resultList: List<ImageBean>): List<MultipartBody.Part> {
//        val requetBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)
//        for (item in resultList) {
//            val file = File(item.imagePath)
//            val imgbody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
//            requetBuilder.addFormDataPart("file", file.name, imgbody)
//        }
//        return requetBuilder.build().parts()
//    }


    /**
     * 计算请求头
     * @param method String 请求方式
     * @param dataMap HashMap<String, Any>?  请求参数
     * @param url String?  请求url
     * @param token String? 登录后的token
     * @return String
     */
//    fun getParamsHeader(method: String, dataMap: LinkedHashMap<String, Any>? = null, url: String? = ""): String {
//        var temp = ""
//        if (null != dataMap) {
//            for (entry in dataMap.entries) {
//                temp = "$temp${entry.key}=${entry.value}&"
//            }
//            temp = temp.substring(0, temp.length - 1)
//        }
//        temp = if (TextUtils.isEmpty(temp)) "" else "?$temp"
//        val syTime = System.currentTimeMillis()
//        val index = url?.indexOf("?")
//        var tempUrl =""
//        if(index !=-1){
//            tempUrl = url?.subSequence(0,index!!)?.toString()!!
//        }else{
//            tempUrl = url
//        }
//        timeHash[tempUrl] = syTime
//        val data = "${method.toUpperCase()}:$url$temp:$syTime"
//        logs("tag", "参与计算的header数据 = $data")
//        return sha1(data)
//    }

//    private fun sha1(data: String): String {
//        val localSecretKeySpec = SecretKeySpec(secreKey.toByteArray(), secreType)//加密密钥
//        val localMac = Mac.getInstance(secreType)
//        localMac.init(localSecretKeySpec)
//        localMac.update(data.toByteArray())
//        return bytes2Hex(localMac.doFinal())
//    }

    private fun bytes2Hex(paramArrayOfByte: ByteArray): String {
        var str1 = ""
        var i = 0
        while (true) {
            if (i >= paramArrayOfByte.size)
                return str1
            val str2 = Integer.toHexString(0xFF and paramArrayOfByte[i].toInt())
            if (str2.length == 1)
                str1 += "0"
            str1 += str2
            i++
        }
    }

    fun getParamsBody(dataMap: HashMap<String, Any>): RequestBody {
        val postInfoStr = Gson().toJson(dataMap)
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postInfoStr)
    }

    fun getParamsBody(data: ArrayList<Any>): RequestBody {
        val postInfoStr = Gson().toJson(data)
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postInfoStr)
    }

    fun getParamsStrBody(data: ArrayList<String>): RequestBody {
        val postInfoStr = Gson().toJson(data)
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postInfoStr)
    }

    fun getParamsBody(data: String): RequestBody {
        val postInfoStr = Gson().toJson(data)
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postInfoStr)
    }

    fun getParamsBody(data: ByteArray): RequestBody {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), data)
    }

    fun getManager(): ApiInter? {
        return retrofit?.create(ApiInter::class.java)
    }

    companion object {
        private val timeHash: Hashtable<String, Long> by lazy { Hashtable<String, Long>() }
        fun getInstance(): HttpNetUtils {
            synchronized(HttpNetUtils::class.java) {
                return HttpNetUtils()
            }
        }
    }
}
