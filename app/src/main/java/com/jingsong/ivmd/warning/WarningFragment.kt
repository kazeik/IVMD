package com.jingsong.ivmd.warning


import com.jingsong.ivmd.R
import com.jingsong.ivmd.mvp.MVPBaseFragment

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class WarningFragment : MVPBaseFragment<WarningContract.View, WarningPresenter>(), WarningContract.View {
    override fun initView(): Int {
        return R.layout.layout_recycler_emtpy
    }

    override fun bindData() {
    }

    override fun lazyLoad() {
    }
}
