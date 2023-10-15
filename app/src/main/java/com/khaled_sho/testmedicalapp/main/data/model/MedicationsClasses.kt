package com.khaled_sho.testmedicalapp.main.data.model

import com.google.gson.annotations.SerializedName


data class MedicationsClasses(
    @SerializedName("className") var className: List<ClassName> = listOf(),
    @SerializedName("className2") var className2: List<ClassName> = listOf()
)