package com.jingsong.ivmd.template


import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jingsong.ivmd.R
import com.jingsong.ivmd.model.TemplateRowModel
import com.jingsong.ivmd.mvp.MVPBaseFragment
import com.jingsong.ivmd.view.DefaultItemDecoration
import com.jingsong.patient.iter.OnItemEventListener
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.fragment_data.*
import kotlinx.android.synthetic.main.fragment_data.btnSearch
import kotlinx.android.synthetic.main.fragment_data.spIp
import kotlinx.android.synthetic.main.fragment_template.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_recycler_emtpy.*
import org.jetbrains.anko.support.v4.toast

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class TemplateFragment : MVPBaseFragment<TemplateContract.View, TemplatePresenter>(),
    TemplateContract.View, XRecyclerView.LoadingListener, OnItemEventListener,
    View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSearch -> mPresenter?.getList(true, 0, name)
        }
    }

    override fun onItemEvent(pos: Int) {
    }

    override fun setData(data: ArrayList<TemplateRowModel>) {
        srvList.refreshComplete()
        srvList.loadMoreComplete()
        if (data.isNullOrEmpty()) {
            srvList.visibility = View.GONE
            rlEmptyView.visibility = View.VISIBLE
            toast("暂无数据")
        } else {
            srvList.visibility = View.VISIBLE
            rlEmptyView.visibility = View.GONE
            adapter.setDataEntityList(data)

            val mItems = ArrayList<String>()
            data.forEach {
                mItems.add(it.peopleName)
            }
            val adapter: ArrayAdapter<String> =
                ArrayAdapter<String>(activity!!, android.R.layout.simple_spinner_item, mItems)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spIp.adapter = adapter
            spIp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    name = mItems[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    private val adapter: TemplateAdapter<TemplateRowModel> by lazy {
        TemplateAdapter<TemplateRowModel>(
            activity!!
        )
    }
    var page: Int? = 0
    var name: String? = ""
    override fun onLoadMore() {
        page = page!! + 1
        mPresenter?.getList(false, page)
    }

    override fun onRefresh() {
        page = 0
        mPresenter?.getList(true)
    }

    override fun initView(): Int {
        return R.layout.fragment_template
    }

    override fun bindData() {
        btnSearch.setOnClickListener(this)
        tvItem1.text = "姓名"
        tvItem2.text = "添加时间"
        tvItem3.text = "模板照片"

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
        if (mPresenter?.allData.isNullOrEmpty())
            mPresenter?.getList()
    }
}
