package com.example.donutapp.screens.onboarding

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.donutapp.R
import com.example.donutapp.composable.PrimaryButton
import com.example.donutapp.mvp.SharedViewModel
import com.example.donutapp.screens.home.navigateToHomeScreen
import com.example.donutapp.ui.theme.Pink
import com.example.donutapp.ui.theme.Pink30
import com.example.donutapp.ui.theme.Pink60
import com.example.donutapp.ui.theme.typography

@Composable
fun OnBoardingScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel =hiltViewModel()

) {
    val state by sharedViewModel.state.observeAsState()
    LaunchedEffect(key1 = true){
        Log.d("HomeScreens", "$state")
        sharedViewModel.updateState()
    }
    OnBoardingContent {
        navController.navigateToHomeScreen()
    }
}

@Composable
fun OnBoardingContent(
    navToHomeScreen: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink30)
    ) {
        val transition = rememberInfiniteTransition()
        val offsetY by transition.animateFloat(
            initialValue = 10f,
            targetValue = 10f,
            animationSpec = infiniteRepeatable(
                animation = tween(5000),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )
        val offsetX by transition.animateFloat(
            initialValue = -20f,
            targetValue = 10f,
            animationSpec = infiniteRepeatable(
                animation = tween(5000),
                repeatMode = RepeatMode.Reverse
            )
        )

        Image(
            modifier = Modifier
                .size(400.dp)
                .padding(top = 64.dp)
                .offset(x = offsetX.dp, y = offsetY.dp)
                .align(Alignment.TopCenter)
                .scale(1.8f),
            painter = painterResource(id = R.drawable.donuts),
            contentDescription = "donuts"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(R.string.boarding_gounts),
                style = typography.headlineLarge.copy(color = Pink)
            )
            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 60.dp),
                text = stringResource(R.string.boarding_details),
                style = typography.bodyMedium.copy(color = Pink60)
            )
            PrimaryButton(text = "Get Started") { navToHomeScreen() }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun BoardingScreenPreview() {
    OnBoardingContent {}
}