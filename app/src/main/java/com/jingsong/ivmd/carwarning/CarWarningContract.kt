package com.jingsong.ivmd.carwarning

import android.content.Context
import com.jingsong.ivmd.model.CarRow
import com.jingsong.ivmd.model.WarningModel

import com.jingsong.ivmd.mvp.BasePresenter
import com.jingsong.ivmd.mvp.BaseView

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class CarWarningContract {
    interface View : BaseView {
        fun setData(data: List<CarRow>)
    }

    interface Presenter : BasePresenter<View> {
        fun search(pageNo: Int? = 0, clear: Boolean? = false,carNo:String?="")
    }
}
