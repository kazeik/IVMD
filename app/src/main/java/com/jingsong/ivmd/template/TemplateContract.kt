package com.jingsong.ivmd.template

import android.content.Context
import com.jingsong.ivmd.model.TemplateRowModel

import com.jingsong.ivmd.mvp.BasePresenter
import com.jingsong.ivmd.mvp.BaseView

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class TemplateContract {
    interface View : BaseView{
        fun setData(data:ArrayList<TemplateRowModel>);
    }

    interface Presenter : BasePresenter<View> {
        fun getList(clear: Boolean? = false, page: Int? = 0,peopleName:String ?="")
    }
}
