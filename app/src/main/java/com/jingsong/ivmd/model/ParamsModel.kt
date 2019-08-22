package com.jingsong.ivmd.model

data class ParamsModel(
    val cameras: List<CameraModel>,
    val tableTime: String,
    val websocketCameras: List<String>
)