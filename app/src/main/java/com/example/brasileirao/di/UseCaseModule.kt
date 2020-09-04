package com.example.brasileirao.di

import com.example.domain.usecases.*
import org.koin.dsl.module

val useCaseModule = module {
    single{ GetGamesUseCase(get()) }
    single{ GetTeamsUseCase(get()) }
    single{ GetTeamsLocalUseCase(get()) }
    single{ InsertTeamsLocalUseCase(get()) }
    single{ InsertGamesLocalUseCase(get()) }
    single{ GetGamesLocalUseCase(get()) }
    single{ GetSingleGameUseCase(get()) }
}