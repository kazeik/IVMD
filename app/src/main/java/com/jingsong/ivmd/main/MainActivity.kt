package com.jingsong.ivmd.main


import com.jingsong.ivmd.mvp.MVPBaseActivity


class MainActivity : MVPBaseActivity<MainContract.View, MainPresenter>(), MainContract.View {
    override fun getLayoutView(): Int {
        return 0
    }

    override fun initData() {
    }
}
