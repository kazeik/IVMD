package com.jingsong.ivmd.video

import android.content.Context
import com.jingsong.ivmd.model.VideoListModel

import com.jingsong.ivmd.mvp.BasePresenter
import com.jingsong.ivmd.mvp.BaseView

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class VideoContract {
    interface View : BaseView{
        fun setVideoListData(data:ArrayList<VideoListModel>)
    }

    interface Presenter : BasePresenter<View>{
        fun getVideoList()
    }
}
