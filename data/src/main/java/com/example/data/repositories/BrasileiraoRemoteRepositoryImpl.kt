package com.example.data.repositories

import com.example.data.base.requestWrapper
import com.example.data.mappers.listToDomain
import com.example.data.mappers.toDomain
import com.example.data.service.BrasileiraoService
import com.example.domain.repositories.BrasileiraoRemoteRepository

class BrasileiraoRemoteRepositoryImpl(
    private val service: BrasileiraoService
) : BrasileiraoRemoteRepository {
    override suspend fun getTeams() =
        requestWrapper {
            service.getTeams().listToDomain()
        }

    override suspend fun getGames() =
        requestWrapper {
            service.getGames().map { it.toDomain() }
        }

}