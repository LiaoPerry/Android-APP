package com.wellmember.app.UI.Base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wellmember.app.Data.Repository.AuthRepository
import com.wellmember.app.Data.Repository.BaseRepository
import com.wellmember.app.UI.Auth.AuthViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}