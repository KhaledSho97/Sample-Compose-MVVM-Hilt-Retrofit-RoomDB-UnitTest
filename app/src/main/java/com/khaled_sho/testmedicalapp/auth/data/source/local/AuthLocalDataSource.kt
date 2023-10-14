package com.khaled_sho.testmedicalapp.auth.data.source.local

import com.khaled_sho.testmedicalapp.auth.data.model.User


interface AuthLocalDataSource {

    suspend fun getUser(): User?


    suspend fun insertUser(user: User?): Long


    suspend fun updateUser(user: User): Int

    suspend fun deleteUser(user: User): Int
    suspend fun deleteAllUsers(): Int

}