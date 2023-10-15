package com.khaled_sho.testmedicalapp.auth.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "user")
class User {

    @Json(name = "id")
    @ColumnInfo(name = "user_id")
    @PrimaryKey
    var id: Int = 0

    @Json(name = "email")
    @ColumnInfo(name = "email")
    var email: String? = null

    @Json(name = "user_name")
    @ColumnInfo(name = "user_name")
    var userName: String? = null

    @Json(name = "password")
    @ColumnInfo(name = "password")
    var password: String? = null

    override fun toString(): String {
        return "user $id  $userName $email"
    }
}
