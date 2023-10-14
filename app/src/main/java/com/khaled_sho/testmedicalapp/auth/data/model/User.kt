package com.khaled_sho.testmedicalapp.auth.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
class User {

    @SerializedName("id")
    @ColumnInfo(name = "user_id")
    @PrimaryKey
    var id: Int = 0

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String? = null

    @SerializedName("user_name")
    @ColumnInfo(name = "user_name")
    var userName: String? = null

    @SerializedName("password")
    @ColumnInfo(name = "password")
    var password: String? = null

    override fun toString(): String {
        return "user $id  $userName $email"
    }
}
