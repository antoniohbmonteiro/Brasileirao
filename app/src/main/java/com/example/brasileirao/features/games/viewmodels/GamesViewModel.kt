package com.example.brasileirao.features.games.viewmodels

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.Cause
import com.example.domain.models.Game
import com.example.domain.models.Team
import com.example.domain.usecases.*
import kotlinx.coroutines.launch

class GamesViewModel(
    private val getGamesUseCase: GetGamesUseCase,
    private val getTeamsUseCase: GetTeamsUseCase,
    private val insertTeamsLocalUseCase: InsertTeamsLocalUseCase,
    private val getGamesLocalUseCase: GetGamesLocalUseCase,
    private val insertGamesLocalUseCase: InsertGamesLocalUseCase
) : ViewModel(), LifecycleObserver {

    val gamesLiveData = MutableLiveData<List<Game>>()

    val loading = MutableLiveData<Boolean>()

    fun start() {
        getTeams()
    }

    fun refreshData() {
        getGames()
    }

    private fun getTeams() {
        viewModelScope.launch {
            getTeamsUseCase.execute(UseCase.None())
                .response(
                    ::showError,
                    ::insertTeams
                )
        }
    }

    private fun insertTeams(teams: List<Team>) {
        viewModelScope.launch {
            insertTeamsLocalUseCase.execute(InsertTeamsLocalUseCase.Params(teams))
                .response(
                    ::showError
                ) { getGames() }
        }
    }

    private fun getGames() {
        viewModelScope.launch {
            getGamesUseCase.execute(UseCase.None())
                .response(
                    ::showError,
                    ::insertGames
                )
        }
    }

    private fun insertGames(games: List<Game>) {
        viewModelScope.launch {
            insertGamesLocalUseCase.execute(InsertGamesLocalUseCase.Params(games))
                .response(
                    ::showError
                ) { getGamesLocal() }
        }
    }

    private fun getGamesLocal() {
        loading.value = false
        viewModelScope.launch {
            getGamesLocalUseCase.execute(UseCase.None())
                .response(
                    ::showError,
                    ::showGames
                )
        }
    }

    private fun showGames(games: List<Game>) {
        gamesLiveData.value = games
    }

    private fun showError(cause: Cause) {
        // ToDo Refactor showError
        Log.i("Antonio", "Error cause: ${cause.throwable}")
    }

}