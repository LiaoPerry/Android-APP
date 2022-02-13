package com.wellmember.app.data.response

data class LoginResponse(
    val access_token: String?,
    val expires_in: Int,
    val token_type: String,
    val user: User
)