package com.khaled_sho.testmedicalapp.main.data.model

import com.google.gson.annotations.SerializedName


data class ClassName(
    @SerializedName("associatedDrug") var associatedDrug: List<AssociatedDrug> = listOf(),
    @SerializedName("associatedDrug#2") var associatedDrug2: List<AssociatedDrug> = listOf()
)