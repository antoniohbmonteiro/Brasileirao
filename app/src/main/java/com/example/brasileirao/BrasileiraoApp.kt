package com.example.brasileirao

import android.app.Application
import com.example.brasileirao.di.presentationModule
import com.example.brasileirao.di.useCaseModule
import com.example.data.di.localModule
import com.example.data.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BrasileiraoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        buildDependencyGraph()
    }

    private fun buildDependencyGraph() {
        startKoin {
            androidLogger()
            androidContext(this@BrasileiraoApp)

            modules(
                presentationModule,
                useCaseModule,
                networkModule,
                localModule
            )
        }
    }

}