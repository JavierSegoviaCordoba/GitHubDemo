package com.javiersc.githubdemo.di

import com.javiersc.githubapi.GitHubApi
import com.javiersc.githubdemo.database.AppDatabase
import com.javiersc.githubdemo.repo.GitHubRepo
import com.javiersc.githubdemo.repo.GitHubRepoImpl
import com.javiersc.githubdemo.ui.detail.DetailViewModel
import com.javiersc.githubdemo.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modulesRepo = module { single<GitHubRepo> { GitHubRepoImpl(get(), get()) } }

val modulesService = module { single { GitHubApi() } }

val modulesDatabase = module { single { AppDatabase.getDatabase(get()).gitHubDAO } }

val modulesUI = module {
    viewModel { MainViewModel(get()) }
    viewModel { (username: String) -> DetailViewModel(get(), username) }
}