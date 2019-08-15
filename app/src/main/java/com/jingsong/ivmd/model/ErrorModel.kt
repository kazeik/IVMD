package com.jingsong.ivmd.model

import com.google.gson.annotations.SerializedName

data class ErrorModel(
        @SerializedName("error")
        val error: String,
        @SerializedName("error_description")
        val errorDescription: String
)