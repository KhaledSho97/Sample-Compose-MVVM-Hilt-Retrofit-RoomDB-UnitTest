package com.khaled_sho.testmedicalapp.main.data.usecase

import com.khaled_sho.testmedicalapp.core.base.model.BaseModel
import com.khaled_sho.testmedicalapp.core.base.model.Result
import com.khaled_sho.testmedicalapp.main.data.model.Problems
import com.khaled_sho.testmedicalapp.main.data.model.ProblemsResponse
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    suspend fun getProblems(): Flow<Result<BaseModel<ProblemsResponse>>>
}