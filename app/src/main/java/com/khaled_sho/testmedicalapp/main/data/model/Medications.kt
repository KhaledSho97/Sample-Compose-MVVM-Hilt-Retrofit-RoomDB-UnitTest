package com.khaled_sho.testmedicalapp.main.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Medications(
    var medicationsClasses:
    List<Map<String, List<Map<String, List<AssociatedDrug>>>>> = listOf()
)