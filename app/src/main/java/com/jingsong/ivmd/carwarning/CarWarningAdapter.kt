package com.jingsong.ivmd.carwarning

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
import com.jingsong.ivmd.model.CarRow
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
class CarWarningAdapter<A>(private val context: Context) : BaseAdapter<A>() {
    internal var itemEventListener: OnItemEventListener? = null


    override fun getLayoutView(): Int {
        return R.layout.adapter_home_data_list
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) {
        val tvId = p0.getViewById<TextView>(R.id.tvId)
        val tvIp = p0.getViewById<TextView>(R.id.tvIp)
        val tvTime = p0.getViewById<TextView>(R.id.tvTime)
        val ivImg = p0.getViewById<ImageView>(R.id.ivImg)

        val entity = dataList?.get(p1) as CarRow

        tvId.text = "${p1 + 1}"
        tvIp.text = if (TextUtils.isEmpty(entity.carNo)) "" else entity.carNo
        tvTime.text = TimeUtil.getDayByType(entity.createTime, TimeUtil.DATE_YMD_HMS)

        Glide.with(context).load(entity.imageUri).asBitmap().override(120, 100)
            .into(ivImg)
    }
}