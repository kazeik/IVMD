package com.jingsong.ivmd.mvp

import android.content.Context

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

interface BaseView {
    fun getMainContext(): Context

    fun showLoading(msg: String? = "", cancel: Boolean = false)

    fun hideLoading()

    fun onReLogin()

    fun showMsg(msg:Int)
    fun showMsg(msg:String)
}
