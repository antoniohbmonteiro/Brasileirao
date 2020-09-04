package com.example.data.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameLocal(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "time")
    val time: String,

    @ColumnInfo(name = "round")
    val round: Int,

    @ColumnInfo(name = "homeTeamId")
    val homeTeamId: Int?,

    @ColumnInfo(name = "guestTeamId")
    val guestTeamId: Int?
)