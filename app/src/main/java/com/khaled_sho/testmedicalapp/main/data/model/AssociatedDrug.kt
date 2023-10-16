package com.khaled_sho.testmedicalapp.main.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AssociatedDrug(
    var name: String? = null,
    var dose: String? = null,
    var strength: String? = null
)