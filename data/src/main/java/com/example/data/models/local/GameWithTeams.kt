package com.example.data.models.local

import androidx.room.Embedded
import androidx.room.Relation

data class GameWithTeams(

    @Embedded
    val game: GameLocal,

    @Relation(parentColumn = "homeTeamId", entityColumn = "id", entity = TeamLocal::class)
    val homeTeam: TeamLocal,

    @Relation(parentColumn = "guestTeamId", entityColumn = "id", entity = TeamLocal::class)
    val guestTeam: TeamLocal

)
