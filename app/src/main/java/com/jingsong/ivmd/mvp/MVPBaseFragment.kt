package com.jingsong.ivmd.mvp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jingsong.ivmd.base.LazyFragment
import com.umeng.analytics.MobclickAgent
import org.jetbrains.anko.support.v4.toast

import java.lang.reflect.ParameterizedType

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */


@Suppress("UNCHECKED_CAST")
abstract class MVPBaseFragment<V : BaseView, T : BasePresenterImpl<V>> : LazyFragment(), BaseView {
    var mPresenter: T? = null
    private val baseActivity by lazy { activity as MVPBaseActivity<V, T> }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = getInstance<T>(this, 1)
        mPresenter?.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null)
            mPresenter?.detachView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(initView(), container, false)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
    }

    override fun showLoading(msg: String?, cancel: Boolean) {
        baseActivity.showLoading(msg, cancel)
    }

    override fun hideLoading() {
        baseActivity.hideLoading()
    }

    override fun showMsg(msg: String) {
        toast(msg)
    }

    override fun showMsg(msg: Int) {
        toast(msg)
    }

    override fun onReLogin() {
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(activity)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(activity)
    }

    abstract fun initView(): Int

    abstract fun bindData()

    override fun getMainContext(): Context {
        return activity!!
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
