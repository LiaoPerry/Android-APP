package com.wellmember.app.data.repository

import com.wellmember.app.data.network.Resource
import com.wellmember.app.data.network.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ) : Resource<T>{

        return withContext(Dispatchers.IO){

            try {
                Resource.Success(apiCall.invoke())
            }catch (throwable: Throwable){

                when(throwable){

                    is HttpException -> {
                        Resource.Failure(true, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true, null, null)
                    }

                }

            }

        }

    }

    suspend fun logout(api: UserApi) = safeApiCall {
        api.logout()
    }

}