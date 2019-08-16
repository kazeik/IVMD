package com.jingsong.ivmd.data


import com.jingsong.ivmd.R
import com.jingsong.ivmd.mvp.MVPBaseFragment

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class DataFragment : MVPBaseFragment<DataContract.View, DataPresenter>(), DataContract.View {
    override fun initView(): Int {
        return R.layout.layout_recycler_emtpy
    }

    override fun bindData() {
    }

    override fun lazyLoad() {
    }
}
