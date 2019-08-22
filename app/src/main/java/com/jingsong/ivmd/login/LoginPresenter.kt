package com.jingsong.ivmd.login

import android.text.TextUtils
import com.jingsong.ivmd.main.MainActivity
import com.jingsong.ivmd.model.ErrorModel
import com.jingsong.ivmd.model.VideoListModel
import com.jingsong.ivmd.mvp.BasePresenterImpl
import com.jingsong.ivmd.net.HttpNetUtils
import com.jingsong.ivmd.net.NetworkScheduler
import com.jingsong.ivmd.net.ProgressSubscriber
import com.jingsong.ivmd.utils.ApiUtils
import com.jingsong.ivmd.utils.ApiUtils.videoListMode
import com.jingsong.ivmd.utils.Utils.logs
import org.jetbrains.anko.startActivity

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class LoginPresenter : BasePresenterImpl<LoginContract.View>(), LoginContract.Presenter {
    override fun getVideoList() {
        HttpNetUtils.getInstance().getManager()?.cameralist()
            ?.compose(NetworkScheduler.compose())
            ?.subscribe(object : ProgressSubscriber<VideoListModel>(mView) {
                override fun onSuccess(data: VideoListModel?, code: Int?) {
                    if (data?.code == "0000") {
                        videoListMode = data
                        val activity: LoginActivity = mView?.getMainContext() as LoginActivity
                        activity.startActivity<MainActivity>()
                        activity.finish()
                    } else {
                        mView?.showMsg(data?.message!!)
                    }
                }
            })
    }

    override fun login(phone: String?, pass: String?) {
        if (TextUtils.isEmpty(phone)) {
            mView?.showMsg("帐号不能为空")
            return
        }
        if (TextUtils.isEmpty(pass)) {
            mView?.showMsg("密码不能为空")
            return
        }
        val map = hashMapOf<String, String>("username" to phone!!, "password" to pass!!, "loginType" to "android")
        HttpNetUtils.getInstance().getManager()?.login(map)?.compose(NetworkScheduler.compose())
            ?.subscribe(object : ProgressSubscriber<ErrorModel>(mView) {
                override fun onSuccess(data: ErrorModel?, code: Int?) {
                    if (data?.code!!) {
                        getVideoList()
                    } else {
                        mView?.showMsg("登录失败，请检查密码是否正确")
                    }
                }
            })
    }
}
