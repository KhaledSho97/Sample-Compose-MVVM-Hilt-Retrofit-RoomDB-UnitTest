package com.khaled_sho.testmedicalapp.core.base.model

import com.google.gson.annotations.SerializedName

data class BaseModel<T>(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: T,
    @SerializedName("message") val message: String,
    @SerializedName("code") var code: Int?,
)