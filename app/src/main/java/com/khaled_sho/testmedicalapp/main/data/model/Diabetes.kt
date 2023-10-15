package com.khaled_sho.testmedicalapp.main.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Diabetes(
    @Json(name = "medications") var medications: List<Medications> = listOf(),
    @Json(name = "labs") var labs: List<Labs> = listOf()
)