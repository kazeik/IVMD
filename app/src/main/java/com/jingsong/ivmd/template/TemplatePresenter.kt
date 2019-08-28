package com.jingsong.ivmd.template

import android.text.TextUtils
import com.jingsong.ivmd.model.TemplateModel
import com.jingsong.ivmd.model.TemplateRowModel
import com.jingsong.ivmd.mvp.BasePresenterImpl
import com.jingsong.ivmd.net.HttpNetUtils
import com.jingsong.ivmd.net.NetworkScheduler
import com.jingsong.ivmd.net.ProgressSubscriber

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class TemplatePresenter : BasePresenterImpl<TemplateContract.View>(), TemplateContract.Presenter {

    internal val allData: ArrayList<TemplateRowModel> by lazy { ArrayList<TemplateRowModel>() };
    override fun getList(clear: Boolean?, page: Int?, peopleName: String?) {
        if (clear!!)
            allData.clear()
        val map = HashMap<String, Any>()
        map["page_no"] = page!!
        map["page_size"] = 10
        if (!TextUtils.isEmpty(peopleName))
            map["peopleName"] = peopleName!!

        val body = HttpNetUtils.getInstance().getParamsBody(map)
        HttpNetUtils.getInstance().getManager()
            ?.getlist(body)
            ?.compose(NetworkScheduler.compose())
            ?.subscribe(object : ProgressSubscriber<TemplateModel>(mView) {
                override fun onSuccess(data: TemplateModel?, code: Int?) {
                    allData.addAll(data?.rows!!)
                    mView?.setData(allData)
                }
            })
    }
}
