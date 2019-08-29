package com.jingsong.ivmd.carwarning


import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jingsong.ivmd.R
import com.jingsong.ivmd.data.HomeDataAdapter
import com.jingsong.ivmd.model.CarRow
import com.jingsong.ivmd.model.WarningModel
import com.jingsong.ivmd.model.WarningRowModel
import com.jingsong.ivmd.mvp.MVPBaseActivity
import com.jingsong.ivmd.view.DefaultItemDecoration
import com.jingsong.patient.iter.OnItemEventListener
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_recycler_emtpy.*
import org.jetbrains.anko.toast
import java.util.ArrayList


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class CarWarningActivity : MVPBaseActivity<CarWarningContract.View, CarWarningPresenter>(),
    CarWarningContract.View, XRecyclerView.LoadingListener, OnItemEventListener,
    View.OnClickListener {
    override fun getLayoutView(): Int {
        return R.layout.activity_car_warning
    }

    override fun setData(data: List<CarRow>) {
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
    }

    override fun onLoadMore() {
        pageNo = pageNo!! + 1
        mPresenter?.search(pageNo)
    }

    override fun onRefresh() {
        pageNo = 0
        mPresenter?.search(pageNo)
    }

    override fun onClick(v: View?) {
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    private val adapter: CarWarningAdapter<CarRow> by lazy {
        CarWarningAdapter<CarRow>(
            this
        )
    }

    private var pageNo: Int? = 0

    override fun initData() {
        supportActionBar?.title = "车辆预警"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tvItem1.text = "预警车牌"
        tvItem2.text = "预警时间"
        tvItem3.text = "历史视频"

        srvList.setLoadingListener(this)
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

        if (mPresenter?.allData.isNullOrEmpty())
            mPresenter?.search(pageNo)
    }
}