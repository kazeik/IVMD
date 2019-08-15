package com.jingsong.ivmd.iter

/**
 * @author jingsong.chen, QQ:77132995, email:kazeik@163.com
 * 2019-08-05 09:15
 * 类说明:
 */
interface DownloadListener {
    fun onStart() //下载开始

    fun onProgress(progress: Int) //下载进度

    fun onFinish(path: String) //下载完成

    fun onFail(errorInfo: String) //下载失败
}