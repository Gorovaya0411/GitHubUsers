package com.example.githubusers.data.model

data class User(
    val followers: Int,
    val following: Int,
    val bio: String,
    val name: String,
    val created_at: String,
    val location: String,
    val avatar_url: String
)