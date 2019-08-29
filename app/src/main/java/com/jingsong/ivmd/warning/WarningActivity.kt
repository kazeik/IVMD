package com.jingsong.ivmd.warning


import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jingsong.ivmd.R
import com.jingsong.ivmd.data.HomeDataAdapter
import com.jingsong.ivmd.model.WarningModel
import com.jingsong.ivmd.model.WarningRowModel
import com.jingsong.ivmd.mvp.MVPBaseActivity
import com.jingsong.ivmd.mvp.MVPBaseFragment
import com.jingsong.ivmd.view.DefaultItemDecoration
import com.jingsong.ivmd.warninginfo.WarningInfoActivity
import com.jingsong.patient.iter.OnItemEventListener
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_recycler_emtpy.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.util.*

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class WarningActivity : MVPBaseActivity<WarningContract.View, WarningPresenter>(),
    OnItemEventListener,
    XRecyclerView.LoadingListener, WarningContract.View, View.OnClickListener {
    override fun getLayoutView(): Int {
        return R.layout.fragment_warning
    }

    override fun setData(data: WarningModel) {
        allrows.addAll(data.rows)
        if (allrows.isNullOrEmpty()) {
            srvList.visibility = View.GONE
            rlEmptyView.visibility = View.VISIBLE
            toast("暂无数据")
        } else {
            srvList.visibility = View.VISIBLE
            rlEmptyView.visibility = View.GONE
            adapter.setDataEntityList(allrows)
        }
        srvList.refreshComplete()
        srvList.loadMoreComplete()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onItemEvent(pos: Int) {
        startActivity<WarningInfoActivity>("item" to allrows[pos])
    }

    override fun onLoadMore() {
        pageNo = pageNo!! + 1
        mPresenter?.search(pageNo)
    }

    override fun onRefresh() {
        pageNo = 0
        allrows.clear()
        mPresenter?.search(pageNo)
    }

    override fun onClick(v: View?) {
        startActivity<WarningInfoActivity>()
    }

    private val adapter: WarningAdapter<WarningRowModel> by lazy {
        WarningAdapter<WarningRowModel>(
            this
        )
    }
    private val allrows: ArrayList<WarningRowModel> by lazy { ArrayList<WarningRowModel>() }

    private var pageNo: Int? = 0

    override fun initData() {
//        tvItem1.text = "预警姓名"
//        tvItem2.text = "预警时间"
//        tvItem3.text = "现场照片"
        supportActionBar?.title = "人脸预警"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

        if (allrows.isNullOrEmpty())
            mPresenter?.search(pageNo)
    }
}
