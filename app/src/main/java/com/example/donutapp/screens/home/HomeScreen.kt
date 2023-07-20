package com.example.donutapp.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.donutapp.R
import com.example.donutapp.composable.RoundedButton
import com.example.donutapp.screens.details.navigateToDetailsScreen
import com.example.donutapp.screens.home.composable.DonutsHomeCard
import com.example.donutapp.screens.home.composable.TopOffersDonutHomeCard
import com.example.donutapp.ui.theme.Background
import com.example.donutapp.ui.theme.Blue
import com.example.donutapp.ui.theme.Pink
import com.example.donutapp.ui.theme.Pink30
import com.example.donutapp.ui.theme.SecondaryText
import com.example.donutapp.ui.theme.typography

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    HomeContent(state, viewModel, navHostController)
}

@Composable
fun HomeContent(
    state: HomeUiState,
    homeInteraction: HomeInteraction,
    navHostController: NavHostController
) {

        LazyColumn(
            modifier = Modifier.fillMaxSize().background(Background),
            contentPadding = PaddingValues(top= 32.dp, bottom = 16.dp)
        ) {

            item {
                HomeHeader(modifier = Modifier.padding(horizontal = 16.dp))
            }

            item {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = stringResource(R.string.today_offers),
                    style = typography.bodyLarge
                )
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(state.topOffers) { index, _ ->
                        val color = if (index % 2 != 0) Blue else Pink30
                        TopOffersDonutHomeCard(backgroundTint = color, state = state.topOffers[index],
                            onClickCard = {
                            navHostController.navigateToDetailsScreen()
                            },
                            onClickIconFavorite = { homeInteraction.onClickCardFavoriteIcon(index) })
                    }
                }
            }

            item {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(R.string.donuts),
                    style = typography.bodyLarge
                )
            }

            item {
                LazyRow(
                    contentPadding = PaddingValues(top = 32.dp, start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(state.donuts) { index, _ ->
                        DonutsHomeCard(state = state.donuts[index])
                    }
                }
            }

    }
}

@Composable
fun HomeHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(R.string.let_s_gonuts),
                style = typography.headlineMedium.copy(color = Pink)
            )
            Text(
                text = stringResource(R.string.order_your_favourite_donuts_from_here),
                style = typography.bodyMedium.copy(color = SecondaryText)
            )
        }

        RoundedButton(
            onClick = { }
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_round_search),
                contentDescription = "Search Icon",
                tint = Pink
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navHostController = NavHostController(LocalContext.current))
}
