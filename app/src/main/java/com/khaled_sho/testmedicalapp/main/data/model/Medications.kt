package com.khaled_sho.testmedicalapp.main.data.model

import com.google.gson.annotations.SerializedName


data class Medications(
    @SerializedName("medicationsClasses") var medicationsClasses: List<MedicationsClasses> = listOf()
)