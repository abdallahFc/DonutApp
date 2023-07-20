package com.example.donutapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.donutapp.screens.details.detailsRoute
import com.example.donutapp.screens.home.homeRoute
import com.example.donutapp.screens.onboarding.boardingRoute

@Composable
fun NavGraph(navHostController: NavHostController, modifier: Modifier) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = AppDestination.BoardingScreen.route
    ) {

        homeRoute(navHostController)

        boardingRoute(navHostController)

        detailsRoute(navHostController)
    }
}