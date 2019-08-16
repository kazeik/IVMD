package com.jingsong.ivmd.model

import com.google.gson.annotations.SerializedName

data class ErrorModel(
        @SerializedName("code")
        val code: Boolean
)