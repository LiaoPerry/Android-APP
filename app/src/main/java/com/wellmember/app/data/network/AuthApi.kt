package com.wellmember.app.data.network

import com.wellmember.app.data.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("api/auth/login")
    suspend fun login(

        @Field("customerid") text: String,
        @Field("password") password: String

    ) : LoginResponse

}