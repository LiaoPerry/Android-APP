package com.wellmember.app.Data.Response

data class LoginResponse(
    val access_token: String,
    val expires_in: Int,
    val token_type: String,
    val user: User
)