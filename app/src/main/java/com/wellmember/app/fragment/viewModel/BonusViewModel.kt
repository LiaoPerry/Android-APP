package com.wellmember.app.fragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BonusViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is bonus Fragment"
    }
    val text: LiveData<String> = _text
}