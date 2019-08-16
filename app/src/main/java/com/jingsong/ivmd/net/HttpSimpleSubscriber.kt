package com.jingsong.ivmd.net

import com.google.gson.JsonSyntaxException
import com.jingsong.ivmd.model.ErrorModel
import com.jingsong.ivmd.utils.Utils.parserJson
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Response
import java.io.EOFException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * @author hope.chen
 *         QQ:77132995 email:kazeik@163.com
 *         2018 11 13 09:13
 * 类说明:
 */
abstract class HttpSimpleSubscriber<T> : Observer<Response<T>> {
    protected var intter: Disposable? = null
    override fun onNext(p0: Response<T>) {
        try {
            when {
                p0.code() in 0..300 -> {
                    onSuccess(p0.body(), p0.code())
                }
                else -> {
//                    val errorStr = p0.errorBody()?.string()
//                    try {
//                        val error = parserJson<ErrorModel>(errorStr!!)
//                        if (p0.code() == 401)
//                            onRelogin()
//                        else
//                            onError(p0.code(), error.errorDescription)
//                    } catch (ex: Exception) {
                        onError(p0.code(), "")
//                    }
                }
            }
        } catch (e: Exception) {
            onError(e)
        }
    }

    override fun onError(e: Throwable) {
        var code = -1
        var errorMsg = e.message
        when (e) {
            is UnknownHostException -> {
                code = -400
                errorMsg = "服务器未找到"
            }
            is SocketTimeoutException -> {
                code = -404
                errorMsg = "服务器连接超时"
            }
            is ConnectException -> {
                code = -405
                errorMsg = "服务器连接异常"
            }
            is IllegalStateException -> {
                code = -405
                errorMsg = "服务器返回数据解析异常"
            }
            is JsonSyntaxException -> {
                code = -406
                errorMsg = "需要自己处理的异常"
            }
            is EOFException -> {
                code = -407
                errorMsg = "字符格式转换错误"
            }
            is ClassCastException -> {
                code = -408
                errorMsg = "类型转换错误"
            }
        }
        onError(code, errorMsg)
        e.printStackTrace()
    }

    override fun onSubscribe(d: Disposable) {
        intter = d
    }

    abstract fun onRelogin()
    abstract fun onSuccess(data: T?, code: Int? = 200)
    abstract fun onError(errorCode: Int, msg: String?)
}