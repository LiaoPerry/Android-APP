package com.wellmember.app.UI.Auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wellmember.app.Data.Network.Resource
import com.wellmember.app.Data.Repository.AuthRepository
import com.wellmember.app.Data.Response.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository : AuthRepository
) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        customerid: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(customerid, password)
    }

}