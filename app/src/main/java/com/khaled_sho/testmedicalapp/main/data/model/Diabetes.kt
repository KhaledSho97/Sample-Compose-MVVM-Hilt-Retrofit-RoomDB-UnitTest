package com.khaled_sho.testmedicalapp.main.data.model

import com.google.gson.annotations.SerializedName
import com.khaled_sho.testmedicalapp.main.data.model.Labs
import com.khaled_sho.testmedicalapp.main.data.model.Medications


data class Diabetes(
    @SerializedName("medications") var medications: List<Medications> = listOf(),
    @SerializedName("labs") var labs: List<Labs> = listOf()
)