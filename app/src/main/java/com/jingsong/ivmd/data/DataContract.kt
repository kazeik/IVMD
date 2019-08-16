package com.jingsong.ivmd.data

import com.jingsong.ivmd.mvp.BasePresenter
import com.jingsong.ivmd.mvp.BaseView

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class DataContract {
    interface View : BaseView

    interface Presenter : BasePresenter<View>
}
