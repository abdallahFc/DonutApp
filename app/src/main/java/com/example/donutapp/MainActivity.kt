package com.example.donutapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.donutapp.composable.ConstantProvider
import com.example.donutapp.screens.home.HomeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.isSystemBarsVisible = false
            ConstantProvider()
        }
    }

}

@Composable
fun ViewModeld(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.timerLiveData.observeAsState()
    val stateFlow by viewModel.timerStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        //viewModel.updateTimerLiveData()
        viewModel.updateTimerStateFlow()
    }
    Log.d("jgjgg", "$stateFlow")
}