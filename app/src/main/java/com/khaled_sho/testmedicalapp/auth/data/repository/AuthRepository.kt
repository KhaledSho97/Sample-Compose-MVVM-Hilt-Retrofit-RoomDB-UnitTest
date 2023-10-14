package com.khaled_sho.testmedicalapp.auth.data.repository

import com.khaled_sho.testmedicalapp.auth.data.model.User
import kotlinx.coroutines.flow.Flow
import com.khaled_sho.testmedicalapp.core.base.model.Result

interface AuthRepository {

    suspend fun getLoggedUser(user_: User): Flow<Result<User>>
    suspend fun getLoggedUser(): Flow<Result<User>>
    suspend fun updateUser(user: User)
    suspend fun insertUser(user: User)
    suspend fun deleteUser(user: User): Int
    suspend fun logout()
}