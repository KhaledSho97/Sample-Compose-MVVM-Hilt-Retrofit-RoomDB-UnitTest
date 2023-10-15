package com.khaled_sho.testmedicalapp.core.base.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseModel<T>(
    @Json(name = "status") val status: String,
    @Json(name = "data") val data: T,
    @Json(name = "message") val message: String,
    @Json(name = "code") var code: Int?,
)