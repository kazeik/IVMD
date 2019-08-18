package com.jingsong.ivmd.video

import android.content.Context
import com.jingsong.ivmd.model.VideoListModel

import com.jingsong.ivmd.mvp.BasePresenterImpl
import com.jingsong.ivmd.net.HttpNetUtils
import com.jingsong.ivmd.net.NetworkScheduler
import com.jingsong.ivmd.net.ProgressSubscriber

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class VideoPresenter : BasePresenterImpl<VideoContract.View>(), VideoContract.Presenter {
    override fun getVideoList() {
        HttpNetUtils.getInstance().getManager()?.cameralist()
            ?.compose(NetworkScheduler.compose())
            ?.subscribe(object : ProgressSubscriber<VideoListModel>(mView) {
                override fun onSuccess(data: VideoListModel?, code: Int?) {
                    if (data?.code == "0000") {
                        mView?.setVideoListData(data)
                    } else {
                        mView?.showMsg(data?.message!!)
                    }
                }
            })
    }
}
