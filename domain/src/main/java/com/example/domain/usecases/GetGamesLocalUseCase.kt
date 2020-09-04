package com.example.domain.usecases

import com.example.domain.base.Cause
import com.example.domain.base.Response
import com.example.domain.models.Game
import com.example.domain.repositories.BrasileiraoLocalRepository

class GetGamesLocalUseCase(
    private val db: BrasileiraoLocalRepository
) : UseCase<List<Game>, UseCase.None> {

    override suspend fun execute(params: UseCase.None): Response<Cause, List<Game>> {

        return when (val response = db.getGames()) {
            is Response.Success -> Response.Success(response.value)
            is Response.Failure -> Response.Failure(response.error)
        }
    }

}