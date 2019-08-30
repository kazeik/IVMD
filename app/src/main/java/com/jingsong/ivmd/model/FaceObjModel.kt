package com.jingsong.ivmd.model

data class FaceObjModel(
    val collectionType: Int,
    val current: FaceVideoItemModel,
    val next: FaceVideoItemModel,
    val playTimes: Int,
    val preview: FaceVideoItemModel,
    val timestamp: Long
)