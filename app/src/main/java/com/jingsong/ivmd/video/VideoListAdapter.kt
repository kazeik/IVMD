package com.jingsong.ivmd.video

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.jingsong.ivmd.R
import com.jingsong.ivmd.base.BaseAdapter
import com.jingsong.ivmd.base.BaseViewHolder
import com.jingsong.ivmd.iter.OnItemLongEventListener
import com.jingsong.ivmd.model.ObjItemModel
import com.jingsong.patient.iter.OnItemEventListener


/**
 * @author kazeik chen
 *         QQ:77132995 email:kazeik@163.com
 *         2019 04 08 14:54
 * 类说明:
 */
@SuppressLint("UseSparseArrays")
class VideoListAdapter<A>() : BaseAdapter<A>() {
    internal var itemEventListener: OnItemEventListener? = null


    override fun getLayoutView(): Int {
        return R.layout.adapter_video_list
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) {
        val tvName = p0.getViewById<TextView>(R.id.tvName)
        val tvId = p0.getViewById<TextView>(R.id.tvId)
        val tvStatus = p0.getViewById<TextView>(R.id.tvStatus)
        val tvIp = p0.getViewById<TextView>(R.id.tvIp)
        val tvTime = p0.getViewById<TextView>(R.id.tvTime)
        val tvLocation = p0.getViewById<TextView>(R.id.tvLocation)
        val llItem = p0.getViewById<LinearLayout>(R.id.llItem)

        val entity = dataList?.get(p1) as ObjItemModel

        llItem.setOnClickListener {
            if (itemEventListener != null) {
                itemEventListener?.onItemEvent(p1)
            }
        }

        tvName.text = "设备名称：${entity.name}"
        tvId.text = "设备id：${entity.id}"
        tvIp.text = "设备ip：${if (TextUtils.isEmpty(entity.ip)) "" else entity.ip}"
        tvStatus.text = "设备状态：${when (entity.status) {
            0 -> "在线"
            1 -> "离线"
            else -> ""
        }}"
        tvTime.text = "告警时间："
        tvLocation.text =
            "设备位置：${if (TextUtils.isEmpty(entity.latitude)) "" else entity.latitude} , ${if (TextUtils.isEmpty(
                    entity.longitude
                )
            ) "" else entity.longitude}"
    }
}