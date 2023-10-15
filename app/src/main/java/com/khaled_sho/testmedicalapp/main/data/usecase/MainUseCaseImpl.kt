package com.khaled_sho.testmedicalapp.main.data.usecase

import com.khaled_sho.testmedicalapp.main.data.repository.MainRepository
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(
    private val repository: MainRepository,
) : MainUseCase {
    override suspend fun getProblems() = repository.getProblems()
}
