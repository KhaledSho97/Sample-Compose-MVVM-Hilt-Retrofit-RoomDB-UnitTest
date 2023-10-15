package com.khaled_sho.testmedicalapp.core.base.model

import org.json.JSONObject
import retrofit2.Response
import java.net.HttpURLConnection


fun <T> Response<BaseModel<T>>.collect() = when {
    this.isSuccessful && !this.body()?.status.equals("success") -> Result.error(
        this.body()?.message ?: "", null
    )

    this.isSuccessful -> Result.success(this.body())
    this.code() == HttpURLConnection.HTTP_UNAUTHORIZED -> Result.authorize()
    else -> {
        val jsonObj = JSONObject(this.errorBody()!!.charStream().readText())
        Result.error(jsonObj.getString("message") ?: "Error")
    }
}
