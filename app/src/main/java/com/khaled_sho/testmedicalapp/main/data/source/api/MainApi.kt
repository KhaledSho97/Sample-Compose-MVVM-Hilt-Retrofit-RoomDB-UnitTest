package com.khaled_sho.testmedicalapp.main.data.source.api

import com.khaled_sho.testmedicalapp.main.data.model.Problems
import com.khaled_sho.testmedicalapp.main.data.model.ProblemsResponse
import com.khaled_sho.testmedicalapp.main.data.source.api.MainApiConstants.GET_MED
import retrofit2.Response
import retrofit2.http.GET

interface MainApi {
    @GET(GET_MED)
    suspend fun getProblems(): Response<ProblemsResponse>
}