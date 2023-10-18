package com.example.donutapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.donutapp.composable.BottomNavBar
import com.example.donutapp.mvp.SharedViewModel
import com.example.donutapp.ui.theme.DonutAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonutApp(
) {
    DonutAppTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomNavBar(navController) }) {
            NavGraph(
                navHostController = navController,
                modifier = Modifier.padding(it),)
        }
    }
}