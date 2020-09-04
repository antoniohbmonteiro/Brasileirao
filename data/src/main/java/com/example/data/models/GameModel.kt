package com.example.data.models

import com.google.gson.annotations.SerializedName

data class GameModel(

    @SerializedName("id")
    val id: Int,

    @SerializedName("timeMandanteId")
    val homeTeamId: Int,

    @SerializedName("timeVisitanteId")
    val guestTeamId: Int,

    @SerializedName("dia")
    val date: String,

    @SerializedName("hora")
    val time: String,

    @SerializedName("rodada")
    val round: Int

)