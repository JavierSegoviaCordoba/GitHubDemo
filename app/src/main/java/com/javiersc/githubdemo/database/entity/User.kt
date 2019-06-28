package com.javiersc.githubdemo.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javiersc.githubapi.dto.UserDTO


@Entity
data class User(
    val avatar_url: String?,
    val html_url: String,
    @PrimaryKey
    val id: Int,
    val login: String
)

fun UserDTO.toUser() = User(avatar_url, html_url, id, login)