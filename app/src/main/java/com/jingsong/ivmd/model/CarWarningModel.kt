package com.jingsong.ivmd.model

data class CarWarningModel(
    val code: String,
    val params: Any,
    val rows: List<CarRow>,
    val total: Int
)