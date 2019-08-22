package com.jingsong.ivmd.data


import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jingsong.ivmd.R
import com.jingsong.ivmd.model.HomeDataModel
import com.jingsong.ivmd.model.RowsModel
import com.jingsong.ivmd.model.VideoListModel
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
import kotlin.collections.ArrayList


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class DataFragment : MVPBaseFragment<DataContract.View, DataPresenter>(), DataContract.View,
    XRecyclerView.LoadingListener, OnItemEventListener,
    View.OnClickListener {
    override fun onItemEvent(pos: Int) {
    }

    override fun onLoadMore() {
        pageNo++
        mPresenter?.search(ipAdd, startDateStr, pageNo, startTime, endTime)
    }

    override fun onRefresh() {
        pageNo = 0
        allrows.clear()
        mPresenter?.search(ipAdd, startDateStr, pageNo, startTime, endTime)
    }

    override fun setHomeData(data: HomeDataModel) {
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

    private val allrows: ArrayList<RowsModel> by lazy { ArrayList<RowsModel>() }
    private val ips = ArrayList<String>()
    override fun setVideoListData(data: VideoListModel) {
        val mItems = ArrayList<String>()
        ips.clear()
        data.obj.forEach {
            if (it.f == 1) {
                mItems.add(it.name)
                ips.add(it.ip)
            }
        }
        ipAdd = ips[0]
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
                ipAdd = ips[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                ipAdd = ips[0]
            }
        }
    }

    private var pageNo: Int = 0
    private var startTime: String? = ""
    private var endTime: String? = ""
    private var startDateStr: String? = ""
    private var ipAdd: String? = ""

    private val adapter: HomeDataAdapter<RowsModel> by lazy { HomeDataAdapter<RowsModel>(activity!!) }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSearch -> mPresenter?.search(ipAdd, startDateStr, pageNo, startTime, endTime)
            R.id.btnStart -> showTimeSelect(true)
            R.id.btnEnd -> showTimeSelect()
            R.id.btnDate -> showDateSelect()
        }
    }

    private fun showTimeSelect(isStart: Boolean? = false) {
        val selectedDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        startDate.set(1990, 0, 1)
        TimePickerBuilder(activity, OnTimeSelectListener { date, _ ->
            val selectTime = TimeUtil.dateToStr(date, TimeUtil.DATE_HM)
            if (isStart!!) {
                btnStart.text = selectTime
                startTime = selectTime
            } else {
                btnEnd.text = selectTime
                endTime = selectTime
            }
        }).setType(booleanArrayOf(false, false, false, true, true, false))
            .setCancelText(getString(R.string.cancel))
            .setSubmitText(getString(R.string.sure))
            .setTitleSize(18)//标题文字大小
            .setTitleText("请选择时间")
            .setOutSideCancelable(true)
            .isCyclic(false)
            .setTitleColor(Color.BLACK)
            .setSubmitColor(Color.BLUE)
            .setCancelColor(Color.BLUE)
            .setDate(selectedDate)
            .setRangDate(startDate, selectedDate)
            .setLabel(
                getString(R.string.cyear),
                getString(R.string.month),
                getString(R.string.week),
                getString(R.string.hour),
                getString(R.string.minute),
                getString(R.string.second)
            )
            .isCenterLabel(false)
            .isDialog(false)
            .build().show()
    }

    private fun showDateSelect() {
        val selectedDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        startDate.set(1990, 0, 1)
        TimePickerBuilder(activity, OnTimeSelectListener { date, _ ->
            val selectTime = TimeUtil.dateToStr(date, TimeUtil.DATE_YMS)
            btnDate.text = selectTime
            startDateStr = selectTime?.replace("-", "")
        }).setType(booleanArrayOf(true, true, true, false, false, false))
            .setCancelText(getString(R.string.cancel))
            .setSubmitText(getString(R.string.sure))
            .setTitleSize(18)//标题文字大小
            .setTitleText("请选择日期")
            .setOutSideCancelable(true)
            .isCyclic(false)
            .setTitleColor(Color.BLACK)
            .setSubmitColor(Color.BLUE)
            .setCancelColor(Color.BLUE)
            .setDate(selectedDate)
            .setRangDate(startDate, selectedDate)
            .setLabel(
                getString(R.string.cyear),
                getString(R.string.month),
                getString(R.string.week),
                getString(R.string.hour),
                getString(R.string.minute),
                getString(R.string.second)
            )
            .isCenterLabel(false)
            .isDialog(false)
            .build().show()
    }

    override fun initView(): Int {
        return R.layout.fragment_data
    }

    override fun bindData() {
        btnSearch.setOnClickListener(this)
        btnDate.setOnClickListener(this)
        btnStart.setOnClickListener(this)
        btnEnd.setOnClickListener(this)

        tvItem1.text = "设备IP"
        tvItem2.text = "抓拍时间"
        tvItem3.text = "现场照片"

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

        val mItems = ArrayList<String>()
        ips.clear()
        ApiUtils.videoListMode?.obj?.forEach {
            if (it.f == 1) {
                mItems.add(it.name)
                ips.add(it.ip)
            }
        }
        ipAdd = ips[0]
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
                ipAdd = ips[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                ipAdd = ips[0]
            }
        }
    }

    override fun lazyLoad() {
    }
}
