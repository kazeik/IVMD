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
    internal val allData: ArrayList<VideoListModel> by lazy { ArrayList<VideoListModel>() }
    override fun getVideoList() {
        HttpNetUtils.getInstance().getManager()?.cameralist()
            ?.compose(NetworkScheduler.compose())
            ?.subscribe(object : ProgressSubscriber<List<VideoListModel>>(mView) {
                override fun onSuccess(data: List<VideoListModel>?, code: Int?) {
                    allData.addAll(data!!)
                    mView?.setVideoListData(allData)
                }
            })
    }
}
