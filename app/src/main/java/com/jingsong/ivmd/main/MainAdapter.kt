package com.jingsong.ivmd.main

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.jingsong.ivmd.R
import com.jingsong.ivmd.base.BaseAdapter
import com.jingsong.ivmd.base.BaseViewHolder
import com.jingsong.patient.iter.OnItemEventListener


/**
 * @author kazeik chen
 *         QQ:77132995 email:kazeik@163.com
 *         2019 01 31 21:13
 * 类说明:
 */
class MainAdapter<A>(private val resArr: IntArray) : BaseAdapter<A>() {
    internal var listener: OnItemEventListener? = null
    override fun getLayoutView(): Int {
        return R.layout.adapter_items
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val ivImg = holder.getViewById<ImageView>(R.id.ivAadapterIcon)
        val tvTitle = holder.getViewById<TextView>(R.id.tvAdapterTitle)
        val rlUserItem = holder.getViewById<RelativeLayout>(R.id.rlUserItem)
        rlUserItem.setOnClickListener { if (null != listener) listener?.onItemEvent(position) }

        val itemstr = dataList?.get(position) as String
        tvTitle.text = itemstr
        if (resArr.isEmpty()) {
            ivImg.visibility = View.GONE
        } else {
            ivImg.visibility = View.VISIBLE
            ivImg.setImageResource(resArr[position])
        }
    }
}