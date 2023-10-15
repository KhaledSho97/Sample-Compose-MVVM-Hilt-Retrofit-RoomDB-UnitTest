package com.khaled_sho.testmedicalapp.main.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AssociatedDrug(
    @Json(name = "name") var name: String? = null,
    @Json(name = "dose") var dose: String? = null,
    @Json(name = "strength") var strength: String? = null
)