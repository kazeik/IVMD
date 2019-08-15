package com.jingsong.patient.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Paint
import android.os.Build
import android.telephony.TelephonyManager
import android.widget.TextView

import android.content.Context.TELEPHONY_SERVICE

/**
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com
 * 2017 03 21  10:38
 * 类说明:
 */
class PhoneUtils(private var _context: Context) {
    private var telephonyManager: TelephonyManager

    val phoneBuild: String
        get() = Build.MODEL

    val company: String
        get() = Build.MANUFACTURER

    val versionCode: Int
        get() {
            try {
                val packageInfo = _context.packageManager.getPackageInfo(_context.packageName, 0)
                return packageInfo.versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return 0
        }

    init {
        telephonyManager = _context.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
    }

    /**
     * 获取TextView所占的宽度
     * @param tvView
     * @return
     */
    fun getTextWidth(tvView: TextView): Float {
        val paint = Paint()
        paint.textSize = tvView.textSize
        return paint.measureText(tvView.text.toString())
    }
}
