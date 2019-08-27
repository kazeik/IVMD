package com.jingsong.ivmd.videoplay


import android.view.MenuItem
import android.view.View
import android.widget.VideoView
import com.jingsong.ivmd.R
import com.jingsong.ivmd.mvp.MVPBaseActivity
import kotlinx.android.synthetic.main.activity_player.*


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
@Deprecated("过时")
class VideoPlayActivity : MVPBaseActivity<VideoPlayContract.View, VideoPlayPresenter>() {


    override fun getLayoutView(): Int {
        return R.layout.activity_player
    }

    override fun initData() {

    }

}
