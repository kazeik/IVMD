package com.jingsong.ivmd.mvp

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.jingsong.ivmd.MainApplication
import com.jingsong.ivmd.R
import com.jingsong.ivmd.utils.TimeUtil
import com.jingsong.ivmd.view.LoadingView
import com.umeng.analytics.MobclickAgent
import org.jetbrains.anko.toast

import java.lang.reflect.ParameterizedType


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

@Suppress("UNCHECKED_CAST")
abstract class MVPBaseActivity<V : BaseView, T : BasePresenterImpl<V>> : AppCompatActivity(),
    BaseView {
    var mPresenter: T? = null
    var myApplicaton: MainApplication? = null
    private var loadingView: LoadingView? = null
    private var isShow = false

    protected val mHandler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            handMsg(msg)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = getInstance<T>(this, 1)
        mPresenter?.attachView(this as V)

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        myApplicaton = application as MainApplication
        myApplicaton?.addActivity(this)

        setContentView(getLayoutView())

        initData()
    }

//    override fun onResume() {
//        super.onResume()
//        val time = TimeUtil.StringTimeToLong("2019-09-30 23:59:00", TimeUtil.DATE_YMD_HMS)
//        if (System.currentTimeMillis() > time) {
//            myApplicaton?.exitApp()
//        }
//    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
//        MobclickAgent.onPageEnd(this.javaClass.name)
    }

    override fun onReLogin() {
//        startActivity<LoginActivity>()
        myApplicaton?.exitApp()
    }

    override fun showMsg(msg: Int) {
        toast(msg)
    }

    override fun showMsg(msg: String) {
        toast(msg)
    }

    abstract fun getLayoutView(): Int

    abstract fun initData()

    open fun handMsg(msg: Message?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter?.detachView()
            mPresenter = null
        }
    }


    override fun showLoading(msg: String?, cancel: Boolean) {
        val almsg = if (TextUtils.isEmpty(msg)) getString(R.string.loadding) else msg
        if (!isShow) {
            isShow = true
            loadingView = LoadingView.getInstance(almsg, cancel)
            loadingView?.show(supportFragmentManager, "dialog")
        }
    }

    override fun hideLoading() {
        if (loadingView?.activity != null) {
            loadingView?.dialog?.dismiss()
            loadingView?.dismissAllowingStateLoss()
            loadingView = null
        }
        isShow = false
    }

    override fun getMainContext(): Context {
        return this
    }

    fun <T> getInstance(o: Any, i: Int): T? {
        try {
            return ((o.javaClass
                .genericSuperclass as ParameterizedType).actualTypeArguments[i] as Class<T>)
                .newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }

        return null
    }
}
