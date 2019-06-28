package com.javiersc.githubapi.datasource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal object GitHubApiServiceBuilder {

    private const val baseUrl = "https://api.github.com/"

    private val retrofit = Retrofit.Builder().apply {
        baseUrl(baseUrl)
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    val service = retrofit.create<GitHubApiService>()

}