package com.example.donutapp.screens.details


import androidx.lifecycle.ViewModel
import com.example.donutapp.screens.details.DetailsInteractionListener
import com.example.donutapp.screens.details.DetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailsViewModelListener @Inject constructor(
) : ViewModel(), DetailsInteractionListener {

    private val _state = MutableStateFlow(DetailsUiState())
    val state = _state.asStateFlow()

    override fun onClickFavorite() {
        _state.update { it.copy(favorite = !_state.value.favorite) }
    }

    override fun onClickPlus() {
        val discount = state.value.discount
        _state.update {
            it.copy(
                quantity = _state.value.quantity + 1,
                price = _state.value.price + discount
            )
        }
    }

    override fun onClickMinus() {
        val discount = state.value.discount
        _state.update {
            it.copy(
                quantity = if (_state.value.quantity > 0) _state.value.quantity - 1 else return,
                price = _state.value.price - discount
            )
        }
    }
}