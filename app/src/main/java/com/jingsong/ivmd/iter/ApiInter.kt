package com.jingsong.ivmd.iter

import com.jingsong.ivmd.model.*
import com.jingsong.ivmd.utils.ApiUtils
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


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


    @POST(ApiUtils.login)
    @FormUrlEncoded
    fun login(@FieldMap map: HashMap<String, String>): Observable<Response<ErrorModel>>

    @POST(ApiUtils.getcameralist)
    fun cameralist(): Observable<Response<VideoListModel>>

    @POST(ApiUtils.blacklist)
    fun getlist(@Body body: RequestBody): Observable<Response<TemplateModel>>


    @POST(ApiUtils.origimageListView)
    fun getorigimageList(@Body body: RequestBody): Observable<Response<HomeDataModel>>

    @POST(ApiUtils.alarmblacklist)
    fun getAlaramList(@Body body: RequestBody): Observable<Response<WarningModel>>

    @POST(ApiUtils.caralarmListView)
    fun getCarNoList(@Body body: RequestBody): Observable<Response<CarWarningModel>>

    @POST(ApiUtils.caralarm)
    fun getVideoInfo(  @Body body: RequestBody): Observable<Response<FaceVideoModel>>

}