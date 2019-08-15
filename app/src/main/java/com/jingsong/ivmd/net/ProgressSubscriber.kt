package com.jingsong.ivmd.net

import com.jingsong.ivmd.R
import com.jingsong.ivmd.mvp.BaseView


/**
 * @author hope.chen
 *         QQ:77132995 email:kazeik@163.com
 *         2018 11 13 09:21
 * 类说明:
 */
abstract class ProgressSubscriber<T>(private val mView: BaseView?, msg: String = "", cancel: Boolean = false) : SimpleSubscriber<T>(mView?.getMainContext()!!) {
    constructor(mView: BaseView?, msg: String = mView?.getMainContext()?.getString(R.string.loadding)!!) : this(mView, msg, false)

    init {
        mView?.showLoading()
    }

    override fun onError(e: Throwable) {
        super.onError(e)
        mView?.hideLoading()
    }

    override fun onComplete() {
        super.onComplete()
        mView?.hideLoading()
    }
}