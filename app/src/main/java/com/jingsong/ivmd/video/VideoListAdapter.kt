package com.jingsong.ivmd.video

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.jingsong.ivmd.R
import com.jingsong.ivmd.base.BaseAdapter
import com.jingsong.ivmd.base.BaseViewHolder
import com.jingsong.ivmd.iter.OnItemLongEventListener
import com.jingsong.patient.iter.OnItemEventListener


/**
 * @author kazeik chen
 *         QQ:77132995 email:kazeik@163.com
 *         2019 04 08 14:54
 * 类说明:
 */
@SuppressLint("UseSparseArrays")
class VideoListAdapter<A>() : BaseAdapter<A>() {
    private var show: Boolean = false
    internal var itemEventListener: OnItemEventListener? = null


    override fun getLayoutView(): Int {
        return R.layout.adapter_video_list
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) {
//        val ivicon = p0.getViewById<ImageView>(R.id.ivicon)
//        val tvNum = p0.getViewById<TextView>(R.id.tvNum)
//        val tvName = p0.getViewById<TextView>(R.id.tvName)
//        val tvInfo = p0.getViewById<TextView>(R.id.tvInfo)
////        val cbSelect = p0.getViewById<CheckBox>(R.id.cbSelect)
//        val rlView = p0.getViewById<RelativeLayout>(R.id.rlView)


//        val entity = dataList?.get(p1) as PatientModel
    }
}