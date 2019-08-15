package com.jingsong.ivmd.iter

import retrofit2.http.Url
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming



/**
 * @author kazeik chen
 *         QQ:77132995 email:kazeik@163.com
 *         2019 04 17 09:51
 * 类说明:
 */
interface ApiInter {

    @Streaming
    @GET
    fun download(@Url url: String): Call<ResponseBody>


}