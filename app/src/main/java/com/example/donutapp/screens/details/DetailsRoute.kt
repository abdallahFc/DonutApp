package com.example.donutapp.screens.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.donutapp.AppDestination


fun NavController.navigateToDetailsScreen() {
    navigate(AppDestination.DetailsScreen.route)
}


fun NavGraphBuilder.detailsRoute(navHostController: NavHostController) {
    composable(AppDestination.DetailsScreen.route) {
        DetailsScreen(navHostController = navHostController)
    }
}