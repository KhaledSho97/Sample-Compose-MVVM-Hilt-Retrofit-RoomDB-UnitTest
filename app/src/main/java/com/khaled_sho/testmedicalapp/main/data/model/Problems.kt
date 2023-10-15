package com.khaled_sho.testmedicalapp.main.data.model

import com.google.gson.annotations.SerializedName


data class Problems(
    @SerializedName("Diabetes") var diabetes: List<Diabetes> = listOf(),
    @SerializedName("Asthma") var asthma: List<Asthma> = listOf()
)