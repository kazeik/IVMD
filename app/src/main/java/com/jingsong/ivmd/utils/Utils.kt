package com.jingsong.ivmd.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.graphics.BitmapFactory
import android.text.TextUtils
import android.util.Base64
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jingsong.ivmd.BuildConfig
import com.jingsong.ivmd.R
import com.jingsong.patient.utils.MD5Utils
import com.jingsong.patient.utils.SdcardUtils
import java.util.regex.Pattern
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


/**
 * @author kazeik.chen, QQ:77132995, email:kazeik@163.com
 * 2018 09 14 20:59
 * 类说明:
 */
object Utils {

    fun logs(cls: Class<*>, msg: String) {
        logs(cls.simpleName, msg)
    }

    fun logs(tag: String? = javaClass.name, msg: String) {
        if (BuildConfig.LOG_DEBUG)
            Log.e(tag, msg)
    }

    fun getAvatar(avatar: String?): String {
        if (TextUtils.isEmpty(avatar)) return ""
        val bitmap = BitmapFactory.decodeFile(avatar)
        val sdcardUtils = SdcardUtils()
        val bytedata = sdcardUtils.bitmap2Bytes(bitmap)
        return Base64.encodeToString(bytedata, Base64.NO_WRAP)
    }

    inline fun <reified T : Any> parserJson(data: String): T {
        return Gson().fromJson(data, T::class.java)
    }

    inline fun <reified T : Any> parserJsonList(data: String): T {
        return Gson().fromJson(data, object : TypeToken<T>() {}.type)
    }

    /**
     * 隐藏输入法
     */
    fun keybordHide(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //  imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);//SHOW_FORCED表示强制显示
        imm.hideSoftInputFromWindow(view.windowToken, 0) //强制隐藏键盘
    }

    fun isPhone(phone: String): Boolean {
        val regex = "^((13[0-9])|(14[0-9])|(15([0-9]))|(16(0-9))|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$"
        if (phone.length != 11) {
            return false
        } else {
            val p = Pattern.compile(regex)
            val m = p.matcher(phone)
            return m.matches()
        }
    }

    @Throws(Exception::class)
    fun encrypt(userIdString: String, regId: String): String {
        val key = MD5Utils.MD5Encode(userIdString, "UTF-8")
        val iv = IvParameterSpec(key.substring(8, 24).toByteArray(charset("UTF-8")))
        val skeySpec = SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)
        val encrypted = cipher.doFinal(regId.toByteArray())
        return Base64.encodeToString(encrypted, Base64.NO_WRAP)
    }

    /**
     * 检测程序是否安装
     *
     * @param packageName
     * @return
     */
    fun isInstalled(mContext: Context, packageName: String): Boolean {
        val installedPackages: List<PackageInfo> = mContext.packageManager.getInstalledPackages(0)
        return installedPackages.any { it.packageName == packageName }
    }

    fun getAndroiodScreenProperty(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels         // 屏幕宽度（像素）
//        val height = dm.heightPixels       // 屏幕高度（像素）
//        val density = dm.density         // 屏幕密度（0.75 / 1.0 / 1.5）
//        val densityDpi = dm.densityDpi     // 屏幕密度dpi（120 / 160 / 240）
//        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
//        val screenWidth = (width / density).toInt()  // 屏幕宽度(dp)
//        val screenHeight = (height / density).toInt()// 屏幕高度(dp)

//        Log.d("h_bl", "屏幕宽度（像素）：$width")
//        Log.d("h_bl", "屏幕高度（像素）：$height")
//        Log.d("h_bl", "屏幕密度（0.75 / 1.0 / 1.5）：$density")
//        Log.d("h_bl", "屏幕密度dpi（120 / 160 / 240）：$densityDpi")
//        Log.d("h_bl", "屏幕宽度（dp）：$screenWidth")
//        Log.d("h_bl", "屏幕高度（dp）：$screenHeight")
        return width
    }
}