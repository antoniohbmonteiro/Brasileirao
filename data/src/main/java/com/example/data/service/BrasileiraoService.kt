package com.example.data.service

import com.example.data.models.GameModel
import com.example.data.models.TeamModel
import retrofit2.http.GET

interface BrasileiraoService {

    @GET("jogos")
    suspend fun getGames(): List<GameModel>

    @GET("times")
    suspend fun getTeams(): List<TeamModel>

}