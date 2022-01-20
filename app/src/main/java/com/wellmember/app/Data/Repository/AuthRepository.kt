package com.wellmember.app.Data.Repository

import com.wellmember.app.Data.Network.AuthApi

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