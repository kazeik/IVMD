package com.jingsong.ivmd.warninginfo


import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jingsong.ivmd.R
import com.jingsong.ivmd.model.VideoInfoModel
import com.jingsong.ivmd.model.WarningRowModel
import com.jingsong.ivmd.mvp.MVPBaseActivity
import com.jingsong.ivmd.palyer.PlayerActivity
import com.jingsong.ivmd.view.DefaultItemDecoration
import com.jingsong.patient.iter.OnItemEventListener
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.layout_recycler_emtpy.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class WarningInfoActivity : MVPBaseActivity<WarningInfoContract.View, WarningInfoPresenter>(),
    WarningInfoContract.View, OnItemEventListener,
    View.OnClickListener {
    override fun getLayoutView(): Int {
        return R.layout.activity_warning_info
    }

    override fun setData(data: ArrayList<VideoInfoModel>) {
        if (data.isNullOrEmpty()) {
            srvList.visibility = View.GONE
            rlEmptyView.visibility = View.VISIBLE
            toast("暂无数据")
        } else {
            srvList.visibility = View.VISIBLE
            rlEmptyView.visibility = View.GONE
            adapter.setDataEntityList(data)
        }
        srvList.refreshComplete()
        srvList.loadMoreComplete()
    }

    override fun onItemEvent(pos: Int) {
        startActivity<PlayerActivity>(
            "name" to mPresenter?.allItem?.get(pos)?.cameraName,
            "url" to mPresenter?.allItem?.get(pos)?.cameraIp
        )
    }


    override fun onClick(v: View?) {
        startActivity<WarningInfoActivity>()
    }

    private val adapter: WarningInfoAdapter<VideoInfoModel> by lazy {
        WarningInfoAdapter<VideoInfoModel>()
    }

    private var warninItem: WarningRowModel? = null
    override fun initData() {
        supportActionBar?.title = "历史视频列表"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        warninItem = intent.getSerializableExtra("item") as? WarningRowModel
        srvList.setLoadingMoreEnabled(false)
        srvList.setPullRefreshEnabled(false)
        srvList.addItemDecoration(
            DefaultItemDecoration(
                resources.getColor(R.color.linegray_color1),
                1,
                1
            )
        )
        srvList.layoutManager = LinearLayoutManager(this)
        srvList.adapter = adapter
        adapter.itemEventListener = this

        mPresenter?.search(warninItem?.cameraIp!!, warninItem?.id!!, warninItem?.timestamp!!)
    }
}
