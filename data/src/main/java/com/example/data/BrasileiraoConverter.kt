package com.example.data

import androidx.room.TypeConverter
import com.example.data.models.local.TeamLocal
import com.google.gson.Gson

class BrasileiraoConverter {
    @TypeConverter
    fun toJson(value: List<TeamLocal>): String {
        return Gson().toJson(value.toTypedArray(), Array<TeamLocal>::class.java)
    }

    @TypeConverter
    fun fromJson(value: String): List<TeamLocal> {
        return Gson().fromJson(value, Array<TeamLocal>::class.java).toList()
    }
}
