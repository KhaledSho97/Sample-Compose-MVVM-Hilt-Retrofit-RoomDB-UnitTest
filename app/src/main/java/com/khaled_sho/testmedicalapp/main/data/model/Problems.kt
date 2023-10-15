package com.khaled_sho.testmedicalapp.main.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Problems(
    @Json(name = "Diabetes") var diabetes: List<Diabetes> = listOf(),
    @Json(name = "Asthma") var asthma: List<Asthma> = listOf()
)