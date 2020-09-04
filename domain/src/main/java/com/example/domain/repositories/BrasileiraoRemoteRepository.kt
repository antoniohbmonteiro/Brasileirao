package com.example.domain.repositories

import com.example.domain.base.Cause
import com.example.domain.base.Response
import com.example.domain.models.Game
import com.example.domain.models.Team

interface BrasileiraoRemoteRepository {

    suspend fun getTeams(): Response<Cause, List<Team>>
    suspend fun getGames(): Response<Cause, List<Game>>

}