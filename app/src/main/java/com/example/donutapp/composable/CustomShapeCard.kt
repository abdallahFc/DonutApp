package com.example.pizza.screen.composable

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.pizza.TicketShape
import com.example.pizza.drawTicketShadow

@Composable
fun CustomShapeCard(
    modifier: Modifier = Modifier,
    circleRadius: Dp,
    cornerSize: CornerSize,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier.drawTicketShadow(
            TicketShape(circleRadius, cornerSize)
        ),
        shape = TicketShape(circleRadius, cornerSize),
        //elevation = CardDefaults.outlinedCardElevation(4.dp)
    ) {
        content()
    }
}