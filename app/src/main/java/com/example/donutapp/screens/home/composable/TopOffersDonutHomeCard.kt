package com.example.donutapp.screens.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.donutapp.composable.FavoriteButton
import com.example.donutapp.screens.home.TopDonutUiState
import com.example.donutapp.ui.theme.Pink30
import com.example.donutapp.ui.theme.SecondaryText
import com.example.donutapp.ui.theme.Shadow
import com.example.donutapp.ui.theme.typography


@Composable
fun TopOffersDonutHomeCard(
    backgroundTint: Color = Pink30,
    state: TopDonutUiState = TopDonutUiState(),
    onClickCard: () -> Unit,
    onClickIconFavorite: () -> Unit
) {

    Box(Modifier.padding(end = 40.dp)) {

        Box(
            modifier = Modifier
                .size(height = 325.dp, width = 193.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(backgroundTint)
                .shadow(elevation = 1.dp, spotColor = Shadow)
                .clickable { onClickCard() }
        ) {

            FavoriteButton(iconState = state.favoriteIcon , roundedSize = 50, onClickIconFavorite = onClickIconFavorite)

            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            ) {

                Column {

                    Text(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = state.title,
                        style = typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = state.description,
                        style = typography.bodySmall.copy(color = SecondaryText)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            modifier = Modifier.padding(end = 4.dp),
                            text = "€${state.price}",
                            style = typography.bodySmall.copy(
                                color = SecondaryText,
                                textDecoration = TextDecoration.LineThrough
                            )
                        )
                        Text(
                            text = "€${state.discount}",
                            style = typography.headlineSmall.copy(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }

        }

        Image(
            modifier = Modifier.offset(x = (60).dp, y = 20.dp),
            painter = painterResource(id = state.image),
            contentDescription = "donut"
        )
    }

}