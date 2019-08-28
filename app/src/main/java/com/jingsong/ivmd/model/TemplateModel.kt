package com.jingsong.ivmd.model

data class TemplateModel(
    val code: String,
    val params: Any,
    val rows: List<TemplateRowModel>,
    val total: Int
)