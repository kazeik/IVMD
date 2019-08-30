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
@SuppressLint("UseSparseArrays")
class WarningAdapter<A>(private val context: Context) : BaseAdapter<A>() {
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
        val tvTitleName1 = p0.getViewById<TextView>(R.id.tvTitleName1)
        val tvTitleName2 = p0.getViewById<TextView>(R.id.tvTitleName2)
        val tvTitleName3 = p0.getViewById<TextView>(R.id.tvTitleName3)
        val tvTitleName4 = p0.getViewById<TextView>(R.id.tvTitleName4)
        val tvSimilarity1 = p0.getViewById<TextView>(R.id.tvSimilarity1)
        val tvSimilarity2 = p0.getViewById<TextView>(R.id.tvSimilarity2)
        val tvSimilarity3 = p0.getViewById<TextView>(R.id.tvSimilarity3)
        val tvSimilarity4 = p0.getViewById<TextView>(R.id.tvSimilarity4)
        val ivImg1 = p0.getViewById<ImageView>(R.id.ivImg1)
        val ivImg2 = p0.getViewById<ImageView>(R.id.ivImg2)
        val ivImg3 = p0.getViewById<ImageView>(R.id.ivImg3)
        val ivImg4 = p0.getViewById<ImageView>(R.id.ivImg4)
        val llItem = p0.getViewById<LinearLayout>(R.id.llItem)


        val entity = dataList?.get(p1) as WarningRowModel

        tvTitleName1.text = "告警图片"
        tvTitleName2.text = "目标人员1"
        tvTitleName3.text = "目标人员2"
        tvTitleName4.text = "目标人员3"


        tvSimilarity1.text = "告警相似度${entity.alarmScore}%"
        tvSimilarity2.text = "告警相似度${entity.alarmTmplScore1}%"
        tvSimilarity3.text = "告警相似度${entity.alarmTmplScore2}%"
        tvSimilarity4.text = "告警相似度${entity.alarmTmplScore3}%"

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
//        rcvList.adapter = ImgItemAdapter<String>(context)
        Glide.with(context).load(entity.cutImageUrl).asBitmap().override(140, 120)
            .error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
            .into(ivImg1)
        Glide.with(context).load(entity.img1).asBitmap().override(140, 120)
            .error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
            .into(ivImg2)
        Glide.with(context).load(entity.img2).asBitmap().override(140, 120)
            .error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
            .into(ivImg3)
        Glide.with(context).load(entity.img3).asBitmap().override(140, 120)
            .error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
            .into(ivImg4)
    }
}