package com.jingsong.ivmd.login


import com.jingsong.ivmd.mvp.BasePresenter
import com.jingsong.ivmd.mvp.BaseView

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class LoginContract {
    interface View : BaseView{
    }

    interface Presenter : BasePresenter<View>{
        fun login(phone:String?,pass:String?)

        fun getVideoList()
    }
}
