package com.khaled_sho.testmedicalapp.auth.data.source.dao

import androidx.room.*
import com.khaled_sho.testmedicalapp.auth.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM user")
    suspend fun select(): User?

    @Update
    suspend fun updateUser(user: User): Int

    @Delete
    suspend fun deleteUser(user: User): Int

    @Query("DELETE FROM user")
    suspend fun deleteAllUser(): Int


}