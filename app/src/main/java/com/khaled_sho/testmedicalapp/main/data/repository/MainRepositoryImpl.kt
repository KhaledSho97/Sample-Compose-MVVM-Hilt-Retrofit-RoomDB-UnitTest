package com.khaled_sho.testmedicalapp.main.data.repository

import com.khaled_sho.testmedicalapp.core.base.model.BaseModel
import com.khaled_sho.testmedicalapp.core.base.model.Result
import com.khaled_sho.testmedicalapp.main.data.model.Problems
import com.khaled_sho.testmedicalapp.main.data.model.ProblemsResponse
import com.khaled_sho.testmedicalapp.main.data.source.remote.MainRemoteDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val remote: MainRemoteDataSource
) : MainRepository {

    override suspend fun getProblems() = flow {
        val response = remote.getProblems()
        if (response.isSuccessful) {
            val baseModel = BaseModel(
                "success", response.body() as ProblemsResponse, "", 200
            )
            emit(Result.success(baseModel))
        } else {
            emit(Result.error("error code: ${response.code()}"))
        }
    }.catch {
        emit(Result.error(it.message ?: "Error"))
    }
}