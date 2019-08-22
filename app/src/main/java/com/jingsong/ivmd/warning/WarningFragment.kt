package com.jingsong.ivmd.warning


import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jingsong.ivmd.R
import com.jingsong.ivmd.data.HomeDataAdapter
import com.jingsong.ivmd.model.RowsModel
import com.jingsong.ivmd.model.WarningModel
import com.jingsong.ivmd.model.WarningRowModel
import com.jingsong.ivmd.mvp.MVPBaseFragment
import com.jingsong.ivmd.utils.ApiUtils
import com.jingsong.ivmd.utils.TimeUtil
import com.jingsong.ivmd.view.DefaultItemDecoration
import com.jingsong.patient.iter.OnItemEventListener
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.fragment_data.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_recycler_emtpy.*
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

        tvItem1.text = "告警姓名"
        tvItem2.text = "告警时间"
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
