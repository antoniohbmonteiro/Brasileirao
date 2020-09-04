package com.example.brasileirao.di

import com.example.brasileirao.features.game_details.viewmodels.GameDetailsViewModel
import com.example.brasileirao.features.games.viewmodels.GamesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        GamesViewModel(
            getGamesUseCase = get(),
            getTeamsUseCase = get(),
            insertTeamsLocalUseCase = get(),
            insertGamesLocalUseCase = get(),
            getGamesLocalUseCase = get()
        )
    }

    viewModel {
        GameDetailsViewModel(
            get()
        )
    }
}