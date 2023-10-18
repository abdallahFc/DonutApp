package com.example.donutapp.screens.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.donutapp.screens.home.DonutsUiState
import com.example.donutapp.ui.theme.Pink
import com.example.donutapp.ui.theme.SecondaryText
import com.example.donutapp.ui.theme.Shadow
import com.example.donutapp.ui.theme.typography


@Composable
fun DonutsHomeCard(
    backgroundTint: Color = Color.White,
    state: DonutsUiState = DonutsUiState()
) {

    Box(modifier = Modifier.padding(top = 32.dp)) {

        Box(
            modifier = Modifier
                .height(111.dp)
                .defaultMinSize(minWidth = 138.dp).graphicsLayer(
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 10.dp
                    ),
                    shadowElevation = 25f,
                    spotShadowColor = Color.Gray,
                ).clip(RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                ))
                .background(backgroundTint)
                .clickable { }
        ) {
            Box(
                modifier = Modifier.padding(top = 16.dp , start = 16.dp, end = 16.dp, bottom = 16.dp).align(Alignment.BottomCenter)
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = state.title,
                        style = typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = SecondaryText
                        )
                    )
                    Text(
                        text = "€${state.price}",
                        style = typography.bodySmall.copy(fontWeight = FontWeight.SemiBold, color = Pink)
                    )
                }
            }

        }
        Image(
            modifier = Modifier.size(128.dp).offset(y = (-65).dp),
            painter = painterResource(id = state.image),
            contentDescription = "donut"
        )
    }

}