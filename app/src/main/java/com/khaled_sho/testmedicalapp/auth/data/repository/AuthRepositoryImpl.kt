package com.khaled_sho.testmedicalapp.auth.data.repository

import com.khaled_sho.testmedicalapp.auth.data.model.User
import com.khaled_sho.testmedicalapp.auth.data.source.local.AuthLocalDataSource
import com.khaled_sho.testmedicalapp.core.base.model.Result
import com.khaled_sho.testmedicalapp.core.base.model.Success
import com.khaled_sho.testmedicalapp.core.data.local.UserCache
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val local: AuthLocalDataSource,
) : AuthRepository {


    override suspend fun updateUser(user: User) {
        local.updateUser(user)
    }

    override suspend fun insertUser(user: User) {
        local.insertUser(user)
    }

    override suspend fun logout() = flow {
        local.deleteAllUsers()
        emit(Success(true))
    }

    override suspend fun getLoggedUser(user_: User) = flow {
        local.insertUser(user_)
        val user = local.getUser()
        if (user == null) emit(Result.authorize())
        else {
            UserCache.user = user
            emit(Result.success(user))
        }
    }.catch {
        emit(Result.error(it.message ?: "Error"))
    }

    override suspend fun getLoggedUser() = flow {
        val user = local.getUser()
        if (user == null) emit(Result.authorize())
        else {
            UserCache.user = user
            emit(Result.success(user))
        }
    }.catch {
        emit(Result.error(it.message ?: "Error"))
    }

    override suspend fun deleteUser(user: User): Int = local.deleteUser(user)

}