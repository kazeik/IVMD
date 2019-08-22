package com.jingsong.ivmd.model

data class WarningModel(
    val code: String,
    val params: WarningParamsModel,
    val rows: List<WarningRowModel>,
    val total: Int
)