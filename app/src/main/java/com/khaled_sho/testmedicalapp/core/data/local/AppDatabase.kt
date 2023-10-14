package com.khaled_sho.testmedicalapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.khaled_sho.testmedicalapp.auth.data.model.User
import com.khaled_sho.testmedicalapp.auth.data.source.dao.UserDao

@Database(entities = [User::class], version = 1, exportSchema = true)
@TypeConverters(AnyTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}