package com.example.donutapp.screens.onboarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.donutapp.AppDestination

fun NavGraphBuilder.boardingRoute(navHostController : NavHostController){
    composable(AppDestination.BoardingScreen.route) { OnBoardingScreen(navHostController) }
}