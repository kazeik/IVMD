package com.jingsong.ivmd.main


import com.jingsong.ivmd.R
import com.jingsong.ivmd.mvp.MVPBaseActivity


class MainActivity : MVPBaseActivity<MainContract.View, MainPresenter>(), MainContract.View {
    override fun getLayoutView(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
    }
}
