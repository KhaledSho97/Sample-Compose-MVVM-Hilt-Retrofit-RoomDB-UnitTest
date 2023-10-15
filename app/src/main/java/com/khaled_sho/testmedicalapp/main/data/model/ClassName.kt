package com.khaled_sho.testmedicalapp.main.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClassName(
    @Json(name = "associatedDrug") var associatedDrug: List<AssociatedDrug> = listOf(),
    @Json(name = "associatedDrug#2") var associatedDrug2: List<AssociatedDrug> = listOf()
)