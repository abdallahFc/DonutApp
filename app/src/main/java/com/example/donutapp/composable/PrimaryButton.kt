package com.example.donutapp.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.donutapp.ui.theme.typography


@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    textColor: Color = Color.Black,
    buttonColor: Color = Color.White,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50.dp)),
        colors = ButtonDefaults.buttonColors(buttonColor),
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = text,
            style = typography.bodyLarge.copy(color = textColor)
        )
    }
}