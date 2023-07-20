package com.example.donutapp.screens.details

import androidx.compose.ui.graphics.Color
import com.example.donutapp.R
import com.example.donutapp.ui.theme.Pink

data class DetailsUiState(
    val image: Int = R.drawable.image_10,
    val backgroundColor : Color = Pink,
    val favorite: Boolean = false,
    val title: String = "Strawberry Wheel",
    val description: String = "These Baked Strawberry Donuts",
    val price: Int = 30,
    val quantity: Int = 1,
    val discount: Int = 15,
)

