package com.javiersc.githubdemo.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javiersc.githubapi.dto.UserDTO


@Entity
data class UserDetail(
    val avatar_url: String?,
    val bio: String?,
    val blog: String?,
    val company: String?,
    val created_at: String?,
    val email: String?,
    val events_url: String?,
    val followers: Int?,
    val followers_url: String?,
    val following: Int?,
    val following_url: String?,
    val gists_url: String?,
    val gravatar_id: String?,
    val hireable: Boolean?,
    val html_url: String?,
    @PrimaryKey
    val id: Int?,
    val location: String?,
    val login: String?,
    val name: String?,
    val node_id: String?,
    val organizations_url: String?,
    val public_gists: Int?,
    val public_repos: Int?,
    val received_events_url: String?,
    val repos_url: String?,
    val site_admin: Boolean?,
    val starred_url: String?,
    val subscriptions_url: String?,
    val type: String?,
    val updated_at: String?,
    val url: String?
)

fun UserDTO.toUserDetail() = UserDetail(
    avatar_url,
    bio,
    blog,
    company,
    created_at,
    email,
    events_url,
    followers,
    followers_url,
    following,
    following_url,
    gists_url,
    gravatar_id,
    hireable,
    html_url,
    id,
    location,
    login,
    name,
    node_id,
    organizations_url,
    public_gists,
    public_repos,
    received_events_url,
    repos_url,
    site_admin,
    starred_url,
    subscriptions_url,
    type,
    updated_at,
    url
)