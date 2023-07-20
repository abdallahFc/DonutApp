package com.example.donutapp.screens.details

import androidx.compose.ui.graphics.Color
import com.example.donutapp.R
import com.example.donutapp.ui.theme.Blue

data class DetailsUiState(
    val image: Int = R.drawable.image_10,
    val backgroundColor : Color = Blue,
    val favorite: Boolean = false,
    val title: String = "Chocolate Wheel",
    val description: String = "A chocolate-flavored donut.",
    val price: Int = 30,
    val quantity: Int = 1,
    val discount: Int = 15,
)

