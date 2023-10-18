package com.example.donutapp.mvp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableLiveData<String>()
    val state = _state
    init {
        _state.value = "State"
    }
    fun updateState(){
        viewModelScope.launch {
            _state.value = "State updated"
        }
    }
}