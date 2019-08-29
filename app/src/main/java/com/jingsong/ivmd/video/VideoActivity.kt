package com.jingsong.ivmd.video


import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jingsong.ivmd.R
import com.jingsong.ivmd.model.ObjItemModel
import com.jingsong.ivmd.model.VideoListModel
import com.jingsong.ivmd.mvp.MVPBaseActivity
import com.jingsong.ivmd.palyer.PlayerActivity
import com.jingsong.ivmd.utils.ApiUtils.videoListMode
import com.jingsong.ivmd.view.DefaultItemDecoration
import com.jingsong.patient.iter.OnItemEventListener
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.layout_recycler_emtpy.*
import org.jetbrains.anko.startActivity

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class VideoActivity : MVPBaseActivity<VideoContract.View, VideoPresenter>(), VideoContract.View,
    OnItemEventListener,
    XRecyclerView.LoadingListener {
    override fun getLayoutView(): Int {
        return R.layout.layout_recycler_emtpy
    }


    private var listModel: VideoListModel? = null
    override fun onLoadMore() {
    }

    override fun onRefresh() {
        mPresenter?.getVideoList()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemEvent(pos: Int) {
        val model = listModel?.obj?.get(pos)
        val url =
            "rtmp://220.173.143.194:9039/live/live_${model?.ip?.substring(
                model.ip.lastIndexOf(".") + 1,
                model.ip.length
            )}"

        startActivity<PlayerActivity>("url" to url, "name" to model?.name)
    }

    override fun setVideoListData(data: VideoListModel) {
        listModel = data
        srvList.loadMoreComplete()
        srvList.refreshComplete()
        if (data.obj.isNotEmpty()) {
            rlEmptyView.visibility = View.GONE
            srvList.visibility = View.VISIBLE
            adapter.setDataEntityList(data.obj)
        } else {
            rlEmptyView.visibility = View.VISIBLE
            srvList.visibility = View.GONE
        }
    }


    private val adapter: VideoListAdapter<ObjItemModel> by lazy { VideoListAdapter<ObjItemModel>() }
    override fun initData() {
        supportActionBar?.title = "实时视频"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        srvList.setLoadingListener(this)
        srvList.setLoadingMoreEnabled(false)
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

        if (videoListMode != null)
            setVideoListData(videoListMode!!)

//        val config = VideoViewManager.getConfig()
//        try {
//            val mPlayerFactoryField = config.javaClass.getDeclaredField("mPlayerFactory")
//            mPlayerFactoryField.isAccessible = true
//            mPlayerFactoryField.set(config, IjkPlayerFactory.create(this))
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
        if (videoListMode == null)
            mPresenter?.getVideoList()
    }

}
