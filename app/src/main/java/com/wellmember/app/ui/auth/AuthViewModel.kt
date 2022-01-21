package com.wellmember.app.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wellmember.app.data.network.Resource
import com.wellmember.app.data.repository.AuthRepository
import com.wellmember.app.data.response.LoginResponse
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