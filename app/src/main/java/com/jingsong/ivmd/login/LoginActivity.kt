package com.jingsong.ivmd.login


import android.view.View
import com.jingsong.ivmd.BuildConfig
import com.jingsong.ivmd.R
import com.jingsong.ivmd.mvp.MVPBaseActivity
import kotlinx.android.synthetic.main.activity_login.*


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class LoginActivity : MVPBaseActivity<LoginContract.View, LoginPresenter>(), LoginContract.View, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
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
