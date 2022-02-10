package com.wellmember.app.data.repository

import com.wellmember.app.data.UserPreferences
import com.wellmember.app.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository(){

    suspend fun login(
        customerid: String,
        password: String
    ) = safeApiCall {
        api.login(customerid, password)
    }

    suspend fun saveAuthToken(token: String){
        preferences.saveAuthToken(token)
    }

}