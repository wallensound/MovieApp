package com.example.movieapp.data.remote.authentication

data class RequestToken(
    val expires_at: String,
    val request_token: String,
    val success: Boolean
)