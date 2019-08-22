package com.jingsong.ivmd.warning

import android.content.Context
import com.jingsong.ivmd.model.WarningModel

import com.jingsong.ivmd.mvp.BasePresenterImpl
import com.jingsong.ivmd.net.HttpNetUtils
import com.jingsong.ivmd.net.NetworkScheduler
import com.jingsong.ivmd.net.ProgressSubscriber

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class WarningPresenter : BasePresenterImpl<WarningContract.View>(), WarningContract.Presenter {
    override fun search(pageNo: Int?) {
        val map = HashMap<String, Any>()
        map["page_size"] = 10
        map["page_no"] = pageNo!! * 10

        val body = HttpNetUtils.getInstance().getParamsBody(map)
        HttpNetUtils.getInstance().getManager()?.getAlaramList(body)
            ?.compose(NetworkScheduler.compose())
            ?.subscribe(object : ProgressSubscriber<WarningModel>(mView) {
                override fun onSuccess(data: WarningModel?, code: Int?) {
                    mView?.setData(data!!)
                }
            })
    }
}
