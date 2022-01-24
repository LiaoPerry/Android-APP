package com.wellmember.app.fragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is question Fragment"
    }
    val text: LiveData<String> = _text
}