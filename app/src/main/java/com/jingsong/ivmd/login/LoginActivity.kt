package com.jingsong.ivmd.login


import android.util.Log
import android.view.View
import com.dueeeke.videoplayer.ijk.IjkPlayerFactory
import com.dueeeke.videoplayer.player.AndroidMediaPlayerFactory
import com.dueeeke.videoplayer.player.PlayerFactory
import com.dueeeke.videoplayer.player.VideoViewManager
import com.jingsong.ivmd.BuildConfig
import com.jingsong.ivmd.R
import com.jingsong.ivmd.mvp.MVPBaseActivity
import com.jingsong.ivmd.palyer.PlayerActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class LoginActivity : MVPBaseActivity<LoginContract.View, LoginPresenter>(), LoginContract.View,
    View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
//            R.id.loginButton -> startActivity<PlayerActivity>("url" to "rtmp://220.173.143.194:9039/live/live_123")
            R.id.loginButton -> mPresenter?.login(etPhone.text.toString(), etLoginVerfiyCode.text.toString())
        }
    }

    override fun getLayoutView(): Int {
        return R.layout.activity_login
    }

    override fun initData() {
        loginButton.setOnClickListener(this)

        if (BuildConfig.DEBUG) {
            etPhone.setText("admin")
            etLoginVerfiyCode.setText("111111")
        }

    }

}
