package com.jingsong.patient.utils

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupWindow
import com.jingsong.patient.R
import com.jingsong.patient.iter.OnItemEventListener
import com.jingsong.patient.iter.OnPopupDissmissListener


/**
 * @author kazeik chen
 *         QQ:77132995 email:kazeik@163.com
 *         2019 04 16 23:01
 * 类说明:
 */
class PopupUtils(private val activity: Activity) {
    fun showPopup(v: View?, data: Array<String>?, width: Int?, itemListener: OnItemEventListener?, dissmissListener: OnPopupDissmissListener? = null) {
        if (v == null || data == null || data.isEmpty()) return
        val contentView = LayoutInflater.from(activity).inflate(R.layout.layout_popup_list, null, false)
        val window = PopupWindow(contentView, width!!, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        with(window) {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            isOutsideTouchable = true
            isTouchable = true
            showAsDropDown(v, 0, 10)
        }
        window.setOnDismissListener {
            if (null != dissmissListener)
                dissmissListener.onDismiss()
        }
        val list = contentView.findViewById<ListView>(R.id.lisview)
        list.adapter = ArrayAdapter<String>(activity, R.layout.textview_center, R.id.textView, data)
        list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            window.dismiss()
            if (null != itemListener)
                itemListener.onItemEvent(position)
        }
    }
}