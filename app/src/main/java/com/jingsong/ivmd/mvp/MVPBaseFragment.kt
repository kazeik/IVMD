package com.jingsong.ivmd.mvp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment

import java.lang.reflect.ParameterizedType

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

abstract class MVPBaseFragment<V : BaseView, T : BasePresenterImpl<V>> : Fragment(), BaseView {
    var mPresenter: T? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = getInstance<T>(this, 1)
        mPresenter!!.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null)
            mPresenter!!.detachView()
    }

    override fun getContext(): Context? {
        return super.getContext()
    }

    fun <T> getInstance(o: Any, i: Int): T? {
        try {
            return ((o.javaClass
                .genericSuperclass as ParameterizedType).actualTypeArguments[i] as Class<T>)
                .newInstance()
        } catch (e: Fragment.InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: ClassCastException) {
            e.printStackTrace()
        } catch (e: java.lang.InstantiationException) {
            e.printStackTrace()
        }

        return null
    }
}
