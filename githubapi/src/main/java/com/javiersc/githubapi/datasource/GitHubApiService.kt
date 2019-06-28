package com.javiersc.githubapi.datasource

import com.javiersc.githubapi.dto.UserDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GitHubApiService {

    @GET("users")
    fun getUsers(): Call<List<UserDTO>>

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<UserDTO>

}