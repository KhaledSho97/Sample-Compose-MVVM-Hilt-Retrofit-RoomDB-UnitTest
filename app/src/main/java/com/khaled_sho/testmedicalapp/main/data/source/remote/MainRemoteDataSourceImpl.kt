package com.khaled_sho.testmedicalapp.main.data.source.remote

import com.khaled_sho.testmedicalapp.core.base.model.BaseModel
import com.khaled_sho.testmedicalapp.main.data.model.Problems
import com.khaled_sho.testmedicalapp.main.data.model.ProblemsResponse
import com.khaled_sho.testmedicalapp.main.data.source.api.MainApi
import retrofit2.Response
import javax.inject.Inject

class MainRemoteDataSourceImpl @Inject constructor(
    private val api: MainApi
) : MainRemoteDataSource {

    override suspend fun getProblems(): Response<ProblemsResponse> {
        return api.getProblems()
    }
}
