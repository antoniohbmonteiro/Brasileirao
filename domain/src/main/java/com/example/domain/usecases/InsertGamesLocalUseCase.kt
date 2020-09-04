package com.example.domain.usecases

import com.example.domain.base.Cause
import com.example.domain.base.Response
import com.example.domain.models.Game
import com.example.domain.repositories.BrasileiraoLocalRepository

class InsertGamesLocalUseCase(
    private val db: BrasileiraoLocalRepository
) : UseCase<Unit, InsertGamesLocalUseCase.Params> {

    override suspend fun execute(params: Params): Response<Cause, Unit> {
        return when (val response = db.insertGames(params.games)) {
            is Response.Success -> Response.Success(Unit)
            is Response.Failure -> Response.Failure(response.error)
        }
    }

    data class Params(
        val games: List<Game>
    )
}