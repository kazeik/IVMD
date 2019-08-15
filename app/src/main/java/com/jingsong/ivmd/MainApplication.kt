package com.jingsong.ivmd

import android.app.Activity
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import java.util.*
import android.app.ActivityManager
import android.content.Context
import android.os.Process


/**
 * @author hope.chen, QQ:77132995, email:kazeik@163.com
 * 2018 09 14 14:07
 * 类说明:
 */
class MainApplication : MultiDexApplication() {
    private var activityList: LinkedList<Activity>? = null

    override fun onCreate() {
        super.onCreate()
        activityList = LinkedList()
        MultiDex.install(this)


        UMConfigure.init(this, "5c7a818c61f564fd9a0015a4", "patient", UMConfigure.DEVICE_TYPE_PHONE, "")
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
        UMConfigure.setLogEnabled(false)
        UMConfigure.setEncryptEnabled(true)
        MobclickAgent.setCatchUncaughtExceptions(true)
    }

    private fun shouldInit(): Boolean {
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val processInfos = am.runningAppProcesses
        val mainProcessName = packageName
        val myPid = Process.myPid()
        return processInfos.any { it.pid == myPid && mainProcessName == it.processName }
    }

    fun addActivity(activity: Activity?) {
        if (null != activity && activityList != null)
            activityList?.add(activity)
    }

    fun exitApp() {
        if (activityList != null && !activityList!!.isEmpty()) {
            for (temp in activityList!!) {
                temp.finish()
            }
            activityList?.clear()
            activityList = null
        }
    }
}