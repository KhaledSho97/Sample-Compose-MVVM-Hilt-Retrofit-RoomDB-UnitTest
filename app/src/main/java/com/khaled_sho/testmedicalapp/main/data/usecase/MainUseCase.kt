package com.khaled_sho.testmedicalapp.main.data.usecase

import com.khaled_sho.testmedicalapp.core.base.model.BaseModel
import com.khaled_sho.testmedicalapp.core.base.model.Result
import com.khaled_sho.testmedicalapp.main.data.model.AssociatedDrug
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    suspend fun getProblems(): Flow<Result<BaseModel<List<AssociatedDrug>>>>
}