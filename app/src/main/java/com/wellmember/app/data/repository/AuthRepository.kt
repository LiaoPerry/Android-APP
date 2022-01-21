package com.wellmember.app.data.repository

import com.wellmember.app.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi
) : BaseRepository(){

    suspend fun login(
        customerid: String,
        password: String
    ) = safeApiCall {
        api.login(customerid, password)
    }

}