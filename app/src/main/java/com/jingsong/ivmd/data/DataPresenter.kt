package com.jingsong.ivmd.data

import android.text.TextUtils
import com.jingsong.ivmd.model.HomeDataModel
import com.jingsong.ivmd.model.VideoListModel
import com.jingsong.ivmd.mvp.BasePresenterImpl
import com.jingsong.ivmd.net.HttpNetUtils
import com.jingsong.ivmd.net.NetworkScheduler
import com.jingsong.ivmd.net.ProgressSubscriber

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class DataPresenter : BasePresenterImpl<DataContract.View>(), DataContract.Presenter {
//    override fun getVideoList() {
//        HttpNetUtils.getInstance().getManager()?.cameralist()
//            ?.compose(NetworkScheduler.compose())
//            ?.subscribe(object : ProgressSubscriber<VideoListModel>(mView) {
//                override fun onSuccess(data: VideoListModel?, code: Int?) {
//                    if (data?.code == "0000") {
//                        mView?.setVideoListData(data)
//                    } else {
//                        mView?.showMsg(data?.message!!)
//                    }
//                }
//            })
//    }

    override fun search(
        ip: String?,
        tableTime: String?,
        page_no: Int,
        startTime: String?,
        endTime: String?
    ) {
//        if (TextUtils.isEmpty(ip)) {
//            mView?.showMsg("设备ip地址不能为空")
//            return
//        }

        if (TextUtils.isEmpty(tableTime)) {
            mView?.showMsg("查询日期不能为空")
            return
        }

        val map = HashMap<String, Any>()
        map["type"] = "android"
        map["cameraIp"] = ip!!
        map["tableTime"] = tableTime!!
        map["page_size"] = 10
        map["page_no"] = page_no
        if (!TextUtils.isEmpty(startTime))
            map["startTime"] = startTime!!
        if (!TextUtils.isEmpty(endTime))
            map["endTime"] = endTime!!

        val body = HttpNetUtils.getInstance().getParamsBody(map)
        HttpNetUtils.getInstance().getManager()?.getorigimageList(body)
            ?.compose(NetworkScheduler.compose())
            ?.subscribe(object : ProgressSubscriber<HomeDataModel>(mView) {
                override fun onSuccess(data: HomeDataModel?, code: Int?) {
                    mView?.setHomeData(data!!)
                }
            })
    }
}
