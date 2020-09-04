package com.example.brasileirao.features.game_details.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.Cause
import com.example.domain.models.Game
import com.example.domain.usecases.GetSingleGameUseCase
import kotlinx.coroutines.launch

class GameDetailsViewModel(private val getSingleGameUseCase: GetSingleGameUseCase) : ViewModel() {

    val gameLiveData = MutableLiveData<Game>()

    fun start(gameId: Int) {
        getGameLocal(gameId)
    }

    private fun getGameLocal(gameId: Int) {
        viewModelScope.launch {
            getSingleGameUseCase
                .execute(GetSingleGameUseCase.Params(gameId))
                .response(
                    ::getGameLocalFailure,
                    ::getGameLocalSuccess
                )
        }
    }

    private fun getGameLocalSuccess(game: Game) {
        gameLiveData.value = game
    }

    private fun getGameLocalFailure(cause: Cause) {

    }

}