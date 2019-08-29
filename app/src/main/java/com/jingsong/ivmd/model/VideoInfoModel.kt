package com.jingsong.ivmd.model

data class VideoInfoModel(
    val filePath: String,
    val fileUrl: String,
    val cameraIp: String,
    val cameraName: String,
    val startTime: String,
    val endTime: String,
    val videoLength: String
)