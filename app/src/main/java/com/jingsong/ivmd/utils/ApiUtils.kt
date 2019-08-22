package com.jingsong.ivmd.utils

import com.jingsong.ivmd.model.VideoListModel


/**
 * @author hope.chen
 *         QQ:77132995 email:kazeik@163.com
 *         2018 11 11 17:38
 * 类说明:
 */
object ApiUtils {
    var videoListMode: VideoListModel?=null

    const val login = "/authen"
    /**
     * 获取所有摄像机信息
     */
    const val getcameralist = "/interface/android/getcameralist"
    /**
     * 获取黑名单人员列表
     */
    const val blacklist="/admin/interface/people/h5View"
    const val alarmblacklist="/admin/interface/alarm/h5View"
    /**
     * 获取抓拍列表
     */
    const val origimageListView="/admin/interface/origimage/listView"
    /**
     * 获取所有摄像机信息
     */
    const val caralarmListView ="/admin/interface/caralarm/listView"
    /**
     * 获取所有黑名单预警列表
     */
    const val caralarm="/admin/interface/video/play"
    /**
     * 根据告警id获取相关告警视频信息
     */
    const val caralarmById="/admin/interface/caralarm/listView"
}

