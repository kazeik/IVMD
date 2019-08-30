package com.jingsong.ivmd.warninginfo

import android.content.Context
import com.jingsong.ivmd.model.*

import com.jingsong.ivmd.mvp.BasePresenterImpl
import com.jingsong.ivmd.net.HttpNetUtils
import com.jingsong.ivmd.net.NetworkScheduler
import com.jingsong.ivmd.net.ProgressSubscriber

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class WarningInfoPresenter : BasePresenterImpl<WarningInfoContract.View>(),
    WarningInfoContract.Presenter {
    internal val allItem: ArrayList<FaceVideoItemModel> by lazy { ArrayList<FaceVideoItemModel>() }
    override fun search(cameraIp: String, id: Int, timestamp: Long) {
        val map = HashMap<String, Any>()
        map["cameraIp"] = cameraIp
        map["id"] = id
        map["timestamp"] = timestamp
        val body = HttpNetUtils.getInstance().getParamsBody(map)
        HttpNetUtils.getInstance().getManager()?.getVideoInfo(body)
            ?.compose(NetworkScheduler.compose())
            ?.subscribe(object : ProgressSubscriber<FaceVideoModel>(mView) {
                override fun onSuccess(data: FaceVideoModel?, code: Int?) {
                    allItem.add(data?.obj?.current!!)
                    allItem.add(data.obj.preview)
                    allItem.add(data.obj.next)

                    mView?.setData(allItem)
                }
            })
    }

}
