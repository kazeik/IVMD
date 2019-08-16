package com.jingsong.ivmd.model

data class ObjItemModel(
    val `open`: Boolean,
    val cameraId: Int,
    val conntype: Int,
    val f: Int,
    val id: Int,
    val ip: String,
    val latitude: String,
    val loginName: String,
    val loginPwd: String,
    val longitude: String,
    val name: String,
    val pId: Int,
    val rtspurl: String,
    val status: Int
)