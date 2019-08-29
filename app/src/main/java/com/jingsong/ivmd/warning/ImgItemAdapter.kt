package com.jingsong.ivmd.warning

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jingsong.ivmd.R
import com.jingsong.ivmd.base.BaseAdapter
import com.jingsong.ivmd.base.BaseViewHolder
import com.jingsong.ivmd.iter.OnItemLongEventListener
import com.jingsong.ivmd.model.ObjItemModel
import com.jingsong.ivmd.model.RowsModel
import com.jingsong.ivmd.model.WarningRowModel
import com.jingsong.ivmd.utils.TimeUtil
import com.jingsong.patient.iter.OnItemEventListener


/**
 * @author kazeik chen
 *         QQ:77132995 email:kazeik@163.com
 *         2019 04 08 14:54
 * 类说明:
 */
@Deprecated("弃用")
@SuppressLint("UseSparseArrays")
class ImgItemAdapter<A>(private val context: Context) : BaseAdapter<A>() {
    internal var itemEventListener: OnItemEventListener? = null


    override fun getLayoutView(): Int {
        return R.layout.activity_facewarning
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) {
        val tvId = p0.getViewById<TextView>(R.id.tvId)
        val tvIp = p0.getViewById<TextView>(R.id.tvIp)
        val tvTime = p0.getViewById<TextView>(R.id.tvTime)
        val tvName = p0.getViewById<TextView>(R.id.tvName)
        val ivImg = p0.getViewById<ImageView>(R.id.ivImg)
        val llItem = p0.getViewById<LinearLayout>(R.id.llItem)


        val entity = dataList?.get(p1) as WarningRowModel

        tvId.text = "序号:${p1 + 1}"
        tvName.text =
            "预警姓名:${if (TextUtils.isEmpty(entity.alarmPeopleName)) "" else entity.alarmPeopleName}"
        tvIp.text = "摄像机名称:${entity.cameraName}"
        tvTime.text = "预警时间:${TimeUtil.getDayByType(entity.createTime, TimeUtil.DATE_YMD_HMS)}"

        llItem.setOnClickListener {
            if (null != itemEventListener)
                itemEventListener?.onItemEvent(p1)
        }

//        rcvList.layoutManager = LinearLayoutManager(context)

//        Glide.with(context).load(entity.cutImageUrl).asBitmap().override(120, 100)
//            .into(ivImg)
    }
}