package com.khaled_sho.testmedicalapp.auth.data.source.local

import com.khaled_sho.testmedicalapp.auth.data.model.User
import com.khaled_sho.testmedicalapp.core.data.local.AppDatabase
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val database: AppDatabase
) : AuthLocalDataSource {

    override suspend fun getUser() = database.userDao().select()

    override suspend fun insertUser(user: User?): Long {
        if (user == null) return -1
        return database.userDao().insert(user)
    }


    override suspend fun updateUser(user: User): Int {
        return database.userDao().updateUser(user)
    }


    override suspend fun deleteUser(user: User): Int {
        return database.userDao().deleteUser(user)
    }

    override suspend fun deleteAllUsers(): Int {
        return database.userDao().deleteAllUser()
    }


}