package com.jingsong.ivmd.warning

import android.content.Context
import com.jingsong.ivmd.model.WarningModel

import com.jingsong.ivmd.mvp.BasePresenter
import com.jingsong.ivmd.mvp.BaseView

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class WarningContract {
    interface View : BaseView{
        fun setData(data: WarningModel)
    }

    interface Presenter : BasePresenter<View> {
        fun search(pageNo: Int? = 0)
    }
}
