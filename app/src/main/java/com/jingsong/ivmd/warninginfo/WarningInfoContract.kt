package com.jingsong.ivmd.warninginfo

import android.content.Context
import com.jingsong.ivmd.model.VideoInfoModel
import com.jingsong.ivmd.model.WarningModel

import com.jingsong.ivmd.mvp.BasePresenter
import com.jingsong.ivmd.mvp.BaseView

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class WarningInfoContract {
    interface View : BaseView{
        fun setData(data: ArrayList<VideoInfoModel>)
    }

    interface Presenter : BasePresenter<View> {
        fun search(cameraIp:String,id:Int,timestamp:Long)
    }
}
