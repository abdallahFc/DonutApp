package com.example.donutapp.screens.home


import com.example.donutapp.R
import java.io.Serializable

data class HomeUiState(
    val topOffers: List<TopDonutUiState> = donutUiStateList,
    val donuts: List<DonutsUiState> = donutsUiStates
)

data class TopDonutUiState(
    var favoriteIcon: Boolean = false,
    val image : Int = 0,
    val title : String = "",
    val description: String = "",
    val price: Int = 0,
    val discount: Int = 0,
): Serializable

data class DonutsUiState(
    val image : Int = 0,
    val title : String = "",
    val price: Int = 0,
)

val donutUiStateList = listOf(
    TopDonutUiState(image = R.drawable.image_10, title = "Strawberry Wheel", description = "These Baked Strawberry Donuts.", price = 20, discount = 15),
    TopDonutUiState(image = R.drawable.image_8, title = "Chocolate Glaze", description = " A chocolate-flavored donut.", price = 30, discount = 25),
    TopDonutUiState(image = R.drawable.image_10, title = "Strawberry Wheel", description = "These Baked Strawberry Donuts.", price = 20, discount = 15),
    TopDonutUiState(image = R.drawable.image_8, title = "Chocolate Glaze", description = " A chocolate-flavored donut.", price = 30, discount = 25),
    TopDonutUiState(image = R.drawable.image_10, title = "Strawberry Wheel", description = "These Baked Strawberry Donuts.", price = 20, discount = 15),
    TopDonutUiState(image = R.drawable.image_8, title = "Chocolate Glaze", description = " A chocolate-flavored donut.", price = 30, discount = 25),
)

val donutsUiStates = listOf(
    DonutsUiState(image = R.drawable.image_10, title = "Strawberry", price = 20),
    DonutsUiState(image = R.drawable.image_8, title = "Chocolate", price = 21),
    DonutsUiState(image = R.drawable.image_10, title = "Strawberry", price = 42),
    DonutsUiState(image = R.drawable.image_8, title = "Chocolate", price = 42),
)