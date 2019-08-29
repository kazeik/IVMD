package com.jingsong.ivmd.carwarning

import android.content.Context
import android.text.TextUtils
import com.jingsong.ivmd.model.CarRow
import com.jingsong.ivmd.model.CarWarningModel

import com.jingsong.ivmd.mvp.BasePresenterImpl
import com.jingsong.ivmd.net.HttpNetUtils
import com.jingsong.ivmd.net.NetworkScheduler
import com.jingsong.ivmd.net.ProgressSubscriber

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class CarWarningPresenter : BasePresenterImpl<CarWarningContract.View>(),
    CarWarningContract.Presenter {

    internal val allData: ArrayList<CarRow> by lazy { ArrayList<CarRow>() }
    override fun search(pageNo: Int?, clear: Boolean?, carNo: String?) {
        val map = HashMap<String, Any>()
        map["page_size"] = 10
        map["page_no"] = pageNo!!
        if (!TextUtils.isEmpty(carNo)) {
            map["carNo"] = carNo!!
        }
        if (clear!!)
            allData.clear()
        val body = HttpNetUtils.getInstance().getParamsBody(map)
        HttpNetUtils.getInstance().getManager()?.getCarNoList(body)
            ?.compose(NetworkScheduler.compose())
            ?.subscribe(object : ProgressSubscriber<CarWarningModel>(mView) {
                override fun onSuccess(data: CarWarningModel?, code: Int?) {
                    allData.addAll(data?.rows!!)
                    mView?.setData(allData)
                }
            })
    }
}
