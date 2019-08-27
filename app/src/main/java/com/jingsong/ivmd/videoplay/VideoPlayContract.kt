package com.jingsong.ivmd.videoplay

import android.content.Context

import com.jingsong.ivmd.mvp.BasePresenter
import com.jingsong.ivmd.mvp.BaseView

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class VideoPlayContract {
    interface View : BaseView

    interface Presenter : BasePresenter<View>
}
