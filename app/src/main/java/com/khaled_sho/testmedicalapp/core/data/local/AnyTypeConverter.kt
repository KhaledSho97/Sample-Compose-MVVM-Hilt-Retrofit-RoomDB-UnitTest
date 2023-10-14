package com.khaled_sho.testmedicalapp.core.data.local

import androidx.room.TypeConverter

class AnyTypeConverter {
    @TypeConverter
    fun fromAny(any: Any): String {
        return any.toString()
    }

    @TypeConverter
    fun toAny(string: String): Any {
        return string
    }
}