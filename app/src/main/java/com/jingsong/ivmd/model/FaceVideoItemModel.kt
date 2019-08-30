package com.jingsong.ivmd.model

data class FaceVideoItemModel(
    val cameraIp: String,
    val cameraName: String,
    val endTime: Long,
    val filePath: String,
    val fileUrl: String,
    val startTime: Long,
    val videoLength: Int
)