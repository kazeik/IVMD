package com.jingsong.ivmd.model

data class HomeDataModel(
    val code: String,
    val params: ParamsModel,
    val rows: List<RowsModel>,
    val total: Int
)