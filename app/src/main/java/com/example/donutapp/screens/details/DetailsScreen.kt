package com.example.donutapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.donutapp.R
import com.example.donutapp.composable.BottomSheet
import com.example.donutapp.composable.FavoriteButtonAnimation
import com.example.donutapp.composable.PrimaryButton
import com.example.donutapp.composable.RoundedButton
import com.example.donutapp.ui.theme.Pink
import com.example.donutapp.ui.theme.PrimaryText
import com.example.donutapp.ui.theme.SecondaryText
import com.example.donutapp.ui.theme.typography

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModelListener = hiltViewModel(),
    navHostController: NavHostController
) {
    val state by viewModel.state.collectAsState()
    DetailsContent(state, { navHostController.popBackStack() }, viewModel)
}

@Composable
fun DetailsContent(
    state: DetailsUiState,
    onClickBack: () -> Unit,
    detailsInteractionListener: DetailsInteractionListener
) {
    Box(
        Modifier
            .background(state.backgroundColor)
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onClickBack() }
                    .padding(4.dp),
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "",
                tint = Pink,
            )
        }
        Image(
            modifier = Modifier
                .scale(1.2f)
                .padding(top = 80.dp)
                .align(Alignment.TopCenter),
            painter = painterResource(id = R.drawable.image_7),
            contentDescription = ""
        )

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {

            BottomSheet(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            ) {
                DetailsBottomSheetContent(state, detailsInteractionListener)
            }


            FavoriteButtonAnimation(
                modifier = Modifier.align(Alignment.TopEnd).padding(end = 8.dp).offset(y = (-30).dp),
                iconState = state.favorite,
                roundedSize = 12,
                onClickIconFavorite = detailsInteractionListener::onClickFavorite
            )
        }

    }
}


@Composable
fun DetailsBottomSheetContent(
    state: DetailsUiState,
    detailsInteractionListener: DetailsInteractionListener
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 32.dp, end = 32.dp, bottom = 16.dp)
    ) {
        Text(text = state.title, style = typography.headlineMedium.copy(color = Pink))
        Text(
            modifier = Modifier.padding(top = 32.dp),
            text = "About Donut",
            style = typography.bodyMedium.copy(color = PrimaryText)
        )
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = state.description,
            style = typography.bodySmall.copy(color = SecondaryText)
        )
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Quantity",
            style = typography.bodyMedium.copy(color = PrimaryText)
        )
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            RoundedButton(
                modifier = Modifier.graphicsLayer(
                    shape = RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomStart = 12.dp,
                        bottomEnd = 12.dp
                    ),
                    shadowElevation = 10f,
                    spotShadowColor = Color.Gray,
                ),
                tintColor = Color.White,
                roundedSize = 12,
                onClick = detailsInteractionListener::onClickMinus
            ) {

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "-",
                    style = typography.bodyLarge.copy(color = Color.Black)
                )
            }

            RoundedButton(
                modifier = Modifier.padding(horizontal = 8.dp),
                roundedSize = 12,
                tintColor = Color.Transparent,
                onClick = {}
            ) {
                Text(modifier = Modifier.padding(horizontal = 16.dp), text = state.quantity.toString())

            }
            RoundedButton(
                roundedSize = 12,
                tintColor = Color.Black,
                onClick = detailsInteractionListener::onClickPlus
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "+",
                    style = typography.bodyLarge.copy(color = Color.White)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "€${state.price}",
                style = typography.headlineMedium
            )
            PrimaryButton(modifier = Modifier.padding(start = 16.dp), buttonColor = Pink, textColor = Color.White, text = "Add to Cart") {}
        }

    }
}

