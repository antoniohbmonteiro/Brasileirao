package com.example.domain.models

data class Game(

    val id: Int,
    val date: String,
    val time: String,
    val round: Int,

    val homeTeamId: Int,
    val guestTeamId: Int,

    val homeTeam: Team?,
    val guestTeam: Team?
)