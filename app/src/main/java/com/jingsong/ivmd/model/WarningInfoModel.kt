package com.jingsong.ivmd.model

data class WarningInfoModel(
    val code: String,
    val content: Any,
    val message: Any,
    val obj: VideoItemModel,
    val page_no: Int,
    val page_size: Int,
    val params: Any,
    val success: Boolean,
    val totalPage: Int,
    val totalResult: Int
)