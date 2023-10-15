package com.khaled_sho.testmedicalapp.main.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProblemsResponse(
    @Json(name = "problems") var problems: List<Problems>? = listOf(),
)