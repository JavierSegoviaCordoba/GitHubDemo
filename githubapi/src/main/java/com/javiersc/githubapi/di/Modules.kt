package com.javiersc.githubapi.di

import com.javiersc.githubapi.datasource.GitHubApiServiceBuilder
import com.javiersc.githubapi.repo.GitHubApiRepo
import com.javiersc.githubapi.repo.GitHubApiRepoImpl
import org.koin.dsl.module

internal val modules = module {
    single<GitHubApiRepo> { GitHubApiRepoImpl(GitHubApiServiceBuilder.service) }
}