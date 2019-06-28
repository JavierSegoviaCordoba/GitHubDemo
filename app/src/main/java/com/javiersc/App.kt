package com.javiersc

import android.app.Application
import com.javiersc.githubdemo.di.modulesDatabase
import com.javiersc.githubdemo.di.modulesRepo
import com.javiersc.githubdemo.di.modulesService
import com.javiersc.githubdemo.di.modulesUI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(modulesRepo, modulesService, modulesDatabase, modulesUI))
        }
    }

}