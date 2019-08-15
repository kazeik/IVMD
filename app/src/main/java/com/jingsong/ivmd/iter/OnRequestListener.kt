package com.jingsong.ivmd.iter

/**
 * @author kazeik chen
 *         QQ:77132995 email:kazeik@163.com
 *         2019 04 16 16:34
 * 类说明:
 */
interface OnRequestListener {
    fun <T> onSuccess(data: T, tag: String?="")
//
//    fun onError(ex: Exception, msg: String)
}