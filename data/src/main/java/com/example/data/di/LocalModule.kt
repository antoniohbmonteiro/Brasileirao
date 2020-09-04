package com.example.data.di

import androidx.room.Room
import com.example.data.local.db.BrasileiraoDatabase
import com.example.data.repositories.BrasileiraoLocalRepositoryImpl
import com.example.domain.repositories.BrasileiraoLocalRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {

    single {
        Room
            .databaseBuilder(
                androidContext(),
                BrasileiraoDatabase::class.java,
                "brasileirao-db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<BrasileiraoDatabase>().teamDao() }
    single { get<BrasileiraoDatabase>().gameDao() }

    single<BrasileiraoLocalRepository> { BrasileiraoLocalRepositoryImpl(get(), get()) }


}