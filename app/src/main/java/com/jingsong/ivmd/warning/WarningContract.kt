package com.jingsong.ivmd.warning

import android.content.Context

import com.jingsong.ivmd.mvp.BasePresenter
import com.jingsong.ivmd.mvp.BaseView

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class WarningContract {
    interface View : BaseView

    interface Presenter : BasePresenter<View>
}
