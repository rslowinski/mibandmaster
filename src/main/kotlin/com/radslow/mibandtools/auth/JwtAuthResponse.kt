package com.radslow.mibandtools.auth

data class JwtAuthResponse(
    val accessToken: String
) {
    val tokenType: String = "Bearer"
}