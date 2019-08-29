package com.jingsong.ivmd.model

data class VideoItemModel(
    val collectionType: Int,
    val current: VideoInfoModel,
    val next: VideoInfoModel,
    val playTimes: Int,
    val preview: VideoInfoModel,
    val timestamp: Long
)