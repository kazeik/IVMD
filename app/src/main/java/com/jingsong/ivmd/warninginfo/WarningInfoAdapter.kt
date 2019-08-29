package com.jingsong.ivmd.warninginfo

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jingsong.ivmd.R
import com.jingsong.ivmd.base.BaseAdapter
import com.jingsong.ivmd.base.BaseViewHolder
import com.jingsong.ivmd.iter.OnItemLongEventListener
import com.jingsong.ivmd.model.ObjItemModel
import com.jingsong.ivmd.model.RowsModel
import com.jingsong.ivmd.model.VideoInfoModel
import com.jingsong.ivmd.model.WarningRowModel
import com.jingsong.ivmd.utils.TimeUtil
import com.jingsong.patient.iter.OnItemEventListener


/**
 * @author kazeik chen
 *         QQ:77132995 email:kazeik@163.com
 *         2019 04 08 14:54
 * 类说明:
 */
@SuppressLint("UseSparseArrays")
class WarningInfoAdapter<A> : BaseAdapter<A>() {
    internal var itemEventListener: OnItemEventListener? = null


    override fun getLayoutView(): Int {
        return R.layout.adapter_warning_info
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) {
        val tvId = p0.getViewById<TextView>(R.id.tvId)
        val tvIp = p0.getViewById<TextView>(R.id.tvIp)
        val tvTime = p0.getViewById<TextView>(R.id.tvTime)

        val entity = dataList?.get(p1) as VideoInfoModel

        tvId.text = when (p1) {
            0 -> "当前"
            1 -> "上一个"
            2 -> "下一个"
            else -> ""
        }
        tvIp.text = if (TextUtils.isEmpty(entity.cameraName)) "" else entity.cameraName
        tvTime.text = entity.cameraIp

    }
}