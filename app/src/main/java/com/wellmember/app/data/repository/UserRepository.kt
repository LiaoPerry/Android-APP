package com.wellmember.app.data.repository

import com.wellmember.app.data.network.UserApi

class UserRepository (
    private val api: UserApi
    ): BaseRepository() {

        suspend fun getUser() = safeApiCall {
            api.getUser()
        }

}