package com.example.domain.repositories

import com.example.domain.base.Cause
import com.example.domain.base.Response
import com.example.domain.models.Game
import com.example.domain.models.Team

interface BrasileiraoLocalRepository {
    suspend fun getTeams(): Response<Cause, List<Team>>

    suspend fun insertTeams(teams: List<Team>) : Response<Cause, Unit>

    suspend fun insertGames(games: List<Game>) : Response<Cause, Unit>

    suspend fun getGames(): Response<Cause, List<Game>>

    suspend fun getGame(id: Int): Response<Cause, Game>
}