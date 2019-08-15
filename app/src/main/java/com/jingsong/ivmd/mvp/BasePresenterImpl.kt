package com.jingsong.ivmd.mvp

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class BasePresenterImpl<V : BaseView> : BasePresenter<V> {
    protected var mView: V? = null
    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
}
