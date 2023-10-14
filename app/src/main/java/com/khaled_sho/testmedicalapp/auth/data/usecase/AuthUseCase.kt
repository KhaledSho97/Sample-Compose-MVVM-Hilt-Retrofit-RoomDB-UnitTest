package com.khaled_sho.testmedicalapp.auth.data.usecase

import com.khaled_sho.testmedicalapp.auth.data.model.User
import com.khaled_sho.testmedicalapp.core.base.model.Result
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    suspend fun getLoggedUser(user_: User): Flow<Result<User>>
    suspend fun getLoggedUser(): Flow<Result<User>>
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User): Int
    suspend fun logout()
}