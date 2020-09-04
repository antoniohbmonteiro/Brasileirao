package com.example.data.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamLocal(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "acronym")
    val acronym: String,

    @ColumnInfo(name = "stadium")
    val stadium: String,

    @ColumnInfo(name = "logo")
    val logo: String
)