package com.jingsong.patient.utils

import android.content.Context
import android.view.View
import android.view.View.MeasureSpec
import android.view.View.MeasureSpec.UNSPECIFIED
import android.view.View.MeasureSpec.makeMeasureSpec




/**
 * @author kazeik chen
 *         QQ:77132995 email:kazeik@163.com
 *         2018 11 27 15:09
 * 类说明:
 */
object ScreenUtil {

    /**
     * 屏幕宽度
     *
     * @param context
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        return context.getResources().getDisplayMetrics().widthPixels
    }

    /**
     * 屏幕高度
     *
     * @param context
     * @return
     */
    fun getScreenHeight(context: Context): Int {
        return context.getResources().getDisplayMetrics().heightPixels
    }

    /**
     * 在onCreate()获得view的高度
     *
     * @param view 控件
     * @return 高度
     */
    fun getViewHeight(view: View): Int {
        val w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(w, h)
        return view.getMeasuredHeight()
    }

    /**
     * 在onCreate()获得view的宽度
     *
     * @param view 控件
     * @return 宽度
     */
    fun getViewWidth(view: View): Int {
        val w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(w, h)
        return view.getMeasuredWidth()
    }
}