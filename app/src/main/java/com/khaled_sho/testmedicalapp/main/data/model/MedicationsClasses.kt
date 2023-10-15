package com.khaled_sho.testmedicalapp.main.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MedicationsClasses(
    @Json(name = "className") var className: List<ClassName> = listOf(),
    @Json(name = "className2") var className2: List<ClassName> = listOf()
)