package com.jingsong.ivmd.video


import android.support.v7.widget.LinearLayoutManager
import com.jingsong.ivmd.R
import com.jingsong.ivmd.model.VideoListModel
import com.jingsong.ivmd.mvp.MVPBaseFragment
import com.jingsong.ivmd.view.DefaultItemDecoration
import com.jingsong.patient.iter.OnItemEventListener
import kotlinx.android.synthetic.main.layout_recycler_emtpy.*

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class VideoFragment : MVPBaseFragment<VideoContract.View, VideoPresenter>(), VideoContract.View ,OnItemEventListener{
    override fun onItemEvent(pos: Int) {
    }

    override fun setVideoListData(data: ArrayList<VideoListModel>) {

    }

    override fun initView(): Int {
        return R.layout.layout_recycler_emtpy
    }
    private val adapter:VideoListAdapter<VideoListModel> by lazy {VideoListAdapter<VideoListModel>()}
    override fun bindData() {
//        srvList.setLoadingListener(this)
        srvList.addItemDecoration(DefaultItemDecoration(resources.getColor(R.color.linegray_color1), 1, 1))
        srvList.layoutManager = LinearLayoutManager(activity)
        srvList.adapter = adapter
        adapter.itemEventListener = this
    }

    override fun lazyLoad() {
        if(mPresenter?.allData.isNullOrEmpty()){
            mPresenter?.getVideoList()
        }
    }
}
