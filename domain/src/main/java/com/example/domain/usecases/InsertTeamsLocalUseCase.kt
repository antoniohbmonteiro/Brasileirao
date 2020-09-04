package com.example.domain.usecases

import com.example.domain.base.Cause
import com.example.domain.base.Response
import com.example.domain.models.Team
import com.example.domain.repositories.BrasileiraoLocalRepository

class InsertTeamsLocalUseCase(
    private val db: BrasileiraoLocalRepository
) : UseCase<Unit, InsertTeamsLocalUseCase.Params> {

    override suspend fun execute(params: Params): Response<Cause, Unit> {
        return when (val response = db.insertTeams(params.teams)) {
            is Response.Success -> Response.Success(Unit)
            is Response.Failure -> Response.Failure(response.error)
        }
    }

    data class Params(
        val teams: List<Team>
    )
}