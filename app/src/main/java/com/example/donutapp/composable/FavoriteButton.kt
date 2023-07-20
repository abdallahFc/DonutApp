package com.example.donutapp.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.donutapp.R
import com.example.donutapp.ui.theme.Pink

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    roundedSize: Int = 8,
    iconState: Boolean,
    onClickIconFavorite: () -> Unit
) {

    val iconColor=if (iconState) Color.White else Pink
    val backgroundIconColor=if (iconState) Pink else Color.White

    RoundedButton(
        modifier = modifier.padding(16.dp),
        tintColor = backgroundIconColor,
        roundedSize = roundedSize,
        onClick = { onClickIconFavorite() },
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_like_heart),
            contentDescription = "like",
            tint = iconColor
        )
    }
}
@Preview(showBackground = true)
@Composable
fun FavoriteButtonAnimationPreview() {
    FavoriteButton(iconState = true, onClickIconFavorite = {})
}