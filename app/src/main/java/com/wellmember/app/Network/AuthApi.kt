package com.wellmember.app.Network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("auth/login")
    fun login(

        @Field("CustomerId") text: String,
        @Field("NetPassword") password: String

    ) : Any

}