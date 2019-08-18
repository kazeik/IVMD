package com.jingsong.ivmd.video


import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jingsong.ivmd.R
import com.jingsong.ivmd.model.ObjItemModel
import com.jingsong.ivmd.model.VideoListModel
import com.jingsong.ivmd.mvp.MVPBaseFragment
import com.jingsong.ivmd.view.DefaultItemDecoration
import com.jingsong.patient.iter.OnItemEventListener
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.layout_recycler_emtpy.*

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class VideoFragment : MVPBaseFragment<VideoContract.View, VideoPresenter>(), VideoContract.View, OnItemEventListener,
    XRecyclerView.LoadingListener {
    override fun onLoadMore() {
    }

    override fun onRefresh() {
        mPresenter?.getVideoList()
    }

    override fun onItemEvent(pos: Int) {
    }

    override fun setVideoListData(data: VideoListModel) {
        srvList.loadMoreComplete()
        srvList.refreshComplete()
        if (data.obj.isNotEmpty()) {
            rlEmptyView.visibility = View.GONE
            srvList.visibility = View.VISIBLE
            adapter.setDataEntityList(data.obj)
        }else{
            rlEmptyView.visibility = View.VISIBLE
            srvList.visibility = View.GONE
        }
    }

    override fun initView(): Int {
        return R.layout.layout_recycler_emtpy
    }

    private val adapter: VideoListAdapter<ObjItemModel> by lazy { VideoListAdapter<ObjItemModel>() }
    override fun bindData() {
        srvList.setLoadingListener(this)
        srvList.setLoadingMoreEnabled(false)
        srvList.addItemDecoration(DefaultItemDecoration(resources.getColor(R.color.linegray_color1), 1, 1))
        srvList.layoutManager = LinearLayoutManager(activity)
        srvList.adapter = adapter
        adapter.itemEventListener = this
    }

    override fun lazyLoad() {
        mPresenter?.getVideoList()
    }
}
