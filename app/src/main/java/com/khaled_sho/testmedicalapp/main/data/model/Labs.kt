package com.khaled_sho.testmedicalapp.main.data.model

import com.google.gson.annotations.SerializedName

data class Labs(
    @SerializedName("missing_field") var missingField: String? = null
)