package com.jingsong.ivmd.warninginfo

import android.content.Context
import com.jingsong.ivmd.model.VideoInfoModel
import com.jingsong.ivmd.model.VideoItemModel

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
    internal val allItem :ArrayList<VideoInfoModel> by lazy { ArrayList<VideoInfoModel>() }
    override fun search(cameraIp: String, id: Int, timestamp: Long) {
        HttpNetUtils.getInstance().getManager()?.getVideoInfo(cameraIp, id, timestamp)
            ?.compose(NetworkScheduler.compose())
            ?.subscribe(object : ProgressSubscriber<VideoItemModel>(mView) {
                override fun onSuccess(data: VideoItemModel?, code: Int?) {
                    allItem.add(data?.current!!)
                    allItem.add(data.preview)
                    allItem.add(data.next)
                }
            })
    }

}
