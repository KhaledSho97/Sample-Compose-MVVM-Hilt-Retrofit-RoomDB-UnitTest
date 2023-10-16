package com.khaled_sho.testmedicalapp.main.data.source.remote

import com.khaled_sho.testmedicalapp.main.data.model.ProblemsResponse
import retrofit2.Response

interface MainRemoteDataSource {

    suspend fun getProblems(): Response<ProblemsResponse>
}

