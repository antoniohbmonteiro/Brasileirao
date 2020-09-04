package com.example.data.mappers

import com.example.data.models.GameModel
import com.example.data.models.TeamModel
import com.example.data.models.local.GameLocal
import com.example.data.models.local.GameWithTeams
import com.example.data.models.local.TeamLocal
import com.example.domain.models.Game
import com.example.domain.models.Team

fun GameModel.toDomain() =
    Game(
        id = id,
        date = date,
        time = time,
        round = round,
        homeTeam = null,
        guestTeam = null,
        homeTeamId = homeTeamId,
        guestTeamId = guestTeamId
    )

fun List<TeamModel>.listToDomain() =
    map {
        it.toDomain()
    }

fun TeamModel.toDomain() =
    Team(
        id = id,
        name = name,
        acronym = acronym,
        stadium = stadium,
        logo = logo
    )

fun List<TeamLocal>.localToDomain() =
    map {
        it.toDomain()
    }

fun TeamLocal.toDomain() =
    Team(
        id = id,
        name = name,
        acronym = acronym,
        stadium = stadium,
        logo = logo
    )

fun Team.toLocal() =
    TeamLocal(
        id = id,
        name = name,
        acronym = acronym,
        stadium = stadium,
        logo = logo
    )

fun Game.toLocal() =
    GameLocal(
        id = id,
        date = date,
        time = time,
        round = round,
        homeTeamId = homeTeamId,
        guestTeamId = guestTeamId
    )

fun GameWithTeams.toDomain() =
    Game(
        id = game.id,
        date = game.date,
        time = game.time,
        round = game.round,
        homeTeam = homeTeam.toDomain(),
        guestTeam = guestTeam.toDomain(),
        homeTeamId = homeTeam.id,
        guestTeamId = guestTeam.id
    )