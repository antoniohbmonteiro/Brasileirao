package com.example.domain.usecases

import com.example.domain.base.Cause
import com.example.domain.base.Response
import com.example.domain.models.Game
import com.example.domain.repositories.BrasileiraoLocalRepository
import com.example.domain.repositories.BrasileiraoRemoteRepository

class GetSingleGameUseCase(
    private val db: BrasileiraoLocalRepository
) : UseCase<Game, GetSingleGameUseCase.Params> {

    override suspend fun execute(params: Params): Response<Cause, Game> {

        return when (val response = db.getGame(params.id)) {
            is Response.Success -> Response.Success(response.value)
            is Response.Failure -> Response.Failure(response.error)
        }
    }

    data class Params(
        val id: Int
    )

}