package com.wellmember.app.ui.Base

import androidx.lifecycle.ViewModel
import com.wellmember.app.data.network.UserApi
import com.wellmember.app.data.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
    private val repository: BaseRepository
): ViewModel(){

    suspend fun logout(api: UserApi) = repository.logout(api)

}