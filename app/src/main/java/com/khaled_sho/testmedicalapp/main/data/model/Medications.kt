package com.khaled_sho.testmedicalapp.main.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Medications(
    @Json(name = "medicationsClasses") var medicationsClasses: List<MedicationsClasses> = listOf()
)