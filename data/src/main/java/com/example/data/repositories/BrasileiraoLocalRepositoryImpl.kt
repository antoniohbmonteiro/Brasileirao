package com.example.data.repositories

import com.example.data.base.transactionWrapper
import com.example.data.local.dao.GameDao
import com.example.data.local.dao.TeamDao
import com.example.data.mappers.localToDomain
import com.example.data.mappers.toDomain
import com.example.data.mappers.toLocal
import com.example.domain.models.Game
import com.example.domain.models.Team
import com.example.domain.repositories.BrasileiraoLocalRepository

class BrasileiraoLocalRepositoryImpl(
    private val teamDao: TeamDao,
    private val gameDao: GameDao
) : BrasileiraoLocalRepository {

    override suspend fun getTeams() =
        transactionWrapper {
            teamDao.getAll().localToDomain()
        }

    override suspend fun insertTeams(teams: List<Team>) =
        transactionWrapper {
            teamDao.insertAll(teams.map { it.toLocal() })
        }

    override suspend fun getGames() =
        transactionWrapper {
            gameDao.getGamesWithTeams().map { it.toDomain() }
        }

    override suspend fun insertGames(games: List<Game>) =
        transactionWrapper {
            gameDao.insertAll(games.map { it.toLocal() })
        }

    override suspend fun getGame(id: Int) =
        transactionWrapper {
            gameDao.getGame(id).toDomain()
        }

}