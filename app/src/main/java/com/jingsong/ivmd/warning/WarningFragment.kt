package com.jingsong.ivmd.warning


import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jingsong.ivmd.R
import com.jingsong.ivmd.data.HomeDataAdapter
import com.jingsong.ivmd.model.WarningModel
import com.jingsong.ivmd.model.WarningRowModel
import com.jingsong.ivmd.mvp.MVPBaseFragment
import com.jingsong.ivmd.view.DefaultItemDecoration
import com.jingsong.patient.iter.OnItemEventListener
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_recycler_emtpy.*
import org.jetbrains.anko.support.v4.toast
import java.util.*

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class WarningFragment : MVPBaseFragment<WarningContract.View, WarningPresenter>(),
    OnItemEventListener,
    XRecyclerView.LoadingListener, WarningContract.View, View.OnClickListener {
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

    private val adapter: HomeDataAdapter<WarningRowModel> by lazy {
        HomeDataAdapter<WarningRowModel>(
            activity!!
        )
    }
    private val allrows: ArrayList<WarningRowModel> by lazy { ArrayList<WarningRowModel>() }

    private var pageNo: Int? = 0
    override fun initView(): Int {
        return R.layout.fragment_warning
    }

    override fun bindData() {

        tvItem1.text = "预警姓名"
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
        srvList.layoutManager = LinearLayoutManager(activity)
        srvList.adapter = adapter
        adapter.itemEventListener = this


    }

    override fun lazyLoad() {
        if (allrows.isNullOrEmpty())
            mPresenter?.search(pageNo)
    }
}
