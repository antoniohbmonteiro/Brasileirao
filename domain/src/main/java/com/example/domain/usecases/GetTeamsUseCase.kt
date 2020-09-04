package com.example.domain.usecases

import com.example.domain.base.Cause
import com.example.domain.base.Response
import com.example.domain.models.Team
import com.example.domain.repositories.BrasileiraoRemoteRepository


class GetTeamsUseCase(
    private val repo: BrasileiraoRemoteRepository
) : UseCase<List<Team>, UseCase.None> {

    override suspend fun execute(params: UseCase.None): Response<Cause, List<Team>> {

        return when (val response = repo.getTeams()) {
            is Response.Success -> Response.Success(response.value)
            is Response.Failure -> Response.Failure(response.error)
        }
    }

}