package com.jingsong.ivmd.net

import android.content.Context
import android.text.TextUtils
import com.jingsong.ivmd.MainApplication
import com.jingsong.ivmd.utils.Utils.logs
import com.jingsong.patient.utils.PreferencesUtils
import org.jetbrains.anko.toast


/**
 * @author kazeik chen
 *         QQ:77132995 email:kazeik@163.com
 *         2018 12 07 21:03
 * 类说明:
 */
abstract class SimpleSubscriber<T>(private val context: Context) : HttpSimpleSubscriber<T>() {
    override fun onRelogin() {
        PreferencesUtils.putString(context, "token", "")
        PreferencesUtils.putString(context, "userid", "")
        PreferencesUtils.putString(context, "username", "")
        val main = context.applicationContext as MainApplication
        main.exitApp()
        context.toast("登录已失效，请重新登录")
    }

    override fun onComplete() {
        if (!intter?.isDisposed!!)
            intter?.dispose()
    }

    override fun onError(errorCode: Int, msg: String?) {
        logs("tga", "错误消息 $msg")
        if (!TextUtils.isEmpty(msg))
            context.toast(msg!!)
    }
}