package com.jingsong.ivmd.data

import com.jingsong.ivmd.model.HomeDataModel
import com.jingsong.ivmd.model.VideoListModel
import com.jingsong.ivmd.mvp.BasePresenter
import com.jingsong.ivmd.mvp.BaseView

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class DataContract {
    interface View : BaseView {
        fun setVideoListData(data: VideoListModel)

        fun setHomeData(data:HomeDataModel)
    }

    interface Presenter : BasePresenter<View> {
        fun search(
            ip: String?,
            tableTime: String?,
            page_no: Int = -1,
            startTime: String? = "",
            endTime: String? = ""
        )

    }
}
