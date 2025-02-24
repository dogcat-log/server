package com.back.dogcatlog.user.dto


data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
