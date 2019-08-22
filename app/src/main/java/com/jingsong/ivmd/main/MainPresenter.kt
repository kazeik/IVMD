package com.jingsong.ivmd.main

import android.content.Context
import com.jingsong.ivmd.model.VideoListModel

import com.jingsong.ivmd.mvp.BasePresenterImpl
import com.jingsong.ivmd.net.HttpNetUtils
import com.jingsong.ivmd.net.NetworkScheduler
import com.jingsong.ivmd.net.ProgressSubscriber
import com.jingsong.ivmd.utils.ApiUtils.videoListMode

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class MainPresenter : BasePresenterImpl<MainContract.View>(), MainContract.Presenter {
}
