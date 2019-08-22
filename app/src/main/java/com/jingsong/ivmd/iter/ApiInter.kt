package com.jingsong.ivmd.iter

import com.jingsong.ivmd.model.ErrorModel
import com.jingsong.ivmd.model.HomeDataModel
import com.jingsong.ivmd.model.VideoListModel
import com.jingsong.ivmd.model.WarningModel
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
    @FormUrlEncoded
    fun getlist(
        @Field("peopleName") peopleName: String, @Field("page_no") page_no: Int? = -1, @Field(
            "page_size"
        ) page_size: Int? = 10
    ): Observable<Response<String>>


    @POST(ApiUtils.origimageListView)
    fun getorigimageList(@Body body: RequestBody): Observable<Response<HomeDataModel>>

    @POST(ApiUtils.alarmblacklist)
    fun getAlaramList(@Body body: RequestBody): Observable<Response<WarningModel>>

    @POST(ApiUtils.caralarmListView)
    @FormUrlEncoded
    fun getCarNoList(
        @Field("carNo") peopleName: String, @Field("page_no") page_no: Int? = -1, @Field(
            "page_size"
        ) page_size: Int? = 10
    ): Observable<Response<String>>

}