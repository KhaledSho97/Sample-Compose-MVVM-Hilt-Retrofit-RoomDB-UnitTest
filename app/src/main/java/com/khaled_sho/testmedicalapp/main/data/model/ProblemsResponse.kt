package com.khaled_sho.testmedicalapp.main.data.model

import com.google.gson.annotations.SerializedName


data class ProblemsResponse(
    @SerializedName("problems") var problems: List<Problems>? = listOf(),
)