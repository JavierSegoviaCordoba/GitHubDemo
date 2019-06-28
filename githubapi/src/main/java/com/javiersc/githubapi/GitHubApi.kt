package com.javiersc.githubapi

import com.javiersc.githubapi.di.modules
import com.javiersc.githubapi.dto.UserDTO
import com.javiersc.githubapi.repo.GitHubApiRepo
import org.koin.core.Koin
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.dsl.koinApplication

class GitHubApi : GitHubApiKoinComponent() {

    private val gitHubApiRepo by inject<GitHubApiRepo>()

    suspend fun getUsers(): List<UserDTO> = gitHubApiRepo.getUsers()
    suspend fun getUser(username: String): UserDTO = gitHubApiRepo.getUser(username)

}

abstract class GitHubApiKoinComponent : KoinComponent {

    companion object {
        val koinApp = koinApplication { modules(modules) }.koin
    }

    override fun getKoin(): Koin = koinApp

}