package com.example.pizza.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.pizza.R
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

val HexagonItemShape = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val minSize = min(size.width, size.height)
        val angleRadians = Math.toRadians(60.0).toFloat()
        val radius = minSize / 2f
        return Outline.Generic(
            Path().apply {
                (0..5).forEach { i ->
                    val currentAngle = angleRadians * i
                    val x = radius + radius * cos(currentAngle)
                    val y = radius + radius * sin(currentAngle)
                    if (i == 0) moveTo(x, y) else lineTo(x, y)
                }
                close()
            }
        )
    }
}


@Composable
fun HexagonItem(text: String, index: Int, hexagonSize: Dp) {
    Box(
        modifier = Modifier
            .offset(y = if (index % 3 == 1) (hexagonSize / 2) - 3.dp else 0.dp)
            .offset(x = if (index % 3 == 0) (hexagonSize / 5) else 0.dp)
            .offset(x = if (index % 3 == 2) -(hexagonSize / 5) else 0.dp)
            .size(hexagonSize)
            .clip(HexagonItemShape)
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
        }
    }
}

@Composable
fun CustomLazyListScreen() {
    val list = remember {
        (0..20).map { "Itemgfgfgfttghgfgfg $it" }.toList()
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp + 80.dp

    BoxWithConstraints(
        Modifier.fillMaxSize()
    ) {
        val itemSize = (if (maxHeight < maxWidth) maxHeight else maxWidth) * .33f

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy((-6).dp)
        ) {
            itemsIndexed(list) { index, item ->
                HexagonItem(
                    text = item,
                    index = index,
                    hexagonSize = itemSize
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewCustomLazyListScreen() {
    CustomLazyListScreen()
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewHexagonItem() {
    HexagonItem(text = "Sample Textfddfdfd", index = 0, hexagonSize = 120.dp)
}