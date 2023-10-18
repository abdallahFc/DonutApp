package com.example.donutapp.screens.home

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(), HomeInteraction {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    override fun onClickCardFavoriteIcon(position: Int) {
        val currentState = _state.value
        val updatedTopOffers = currentState.topOffers.mapIndexed { index, donutUiState ->
            if (index == position) {
                donutUiState.copy(favoriteIcon = !donutUiState.favoriteIcon)
            } else {
                donutUiState
            }
        }
        val updatedState = currentState.copy(topOffers = updatedTopOffers)
        _state.value = updatedState

    }

















    private val _timerLiveData=MutableLiveData<Int>()
    val timerLiveData=_timerLiveData

    private val _timerStateFlow=MutableStateFlow(0)
    val timerStateFlow=_timerStateFlow.asStateFlow()
    fun updateTimerStateFlow(){
        viewModelScope.launch {
            //val list= listOf(1,1,1,2,,2,3,4,5,6,7,8,9,10)
            for (i in 0..1000){
                _timerStateFlow.emit(i)
                delay(1000L)
            }
        }
    }
    fun updateTimerLiveData(){
        viewModelScope.launch {
            //val list= listOf(1,1,1,2,,2,3,4,5,6,7,8,9,10)
            for (i in 0..1000){
                _timerLiveData.value=i
                delay(1000L)
            }
        }
    }
}

