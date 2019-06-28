package com.javiersc.githubapi.repo

import com.javiersc.githubapi.datasource.GitHubApiService
import com.javiersc.githubapi.dto.UserDTO
import retrofit2.await

internal interface GitHubApiRepo {
    suspend fun getUsers(): List<UserDTO>
    suspend fun getUser(username: String): UserDTO
}

internal class GitHubApiRepoImpl(private val service: GitHubApiService) : GitHubApiRepo {
    override suspend fun getUsers(): List<UserDTO> = service.getUsers().await()

    override suspend fun getUser(username: String): UserDTO = service.getUser(username).await()
}