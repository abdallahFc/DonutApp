package com.example.pizza.screen.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.donutapp.R
import com.example.pizza.halfCircleShadow



@Composable
fun TicketShapeScreen() {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background( Color(0xFFFDFDFD))
        ) {
            Image(
                modifier = Modifier
                    .weight(0.1f)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.cele),
                contentDescription = null
            )
            Box(
                modifier = Modifier
                    .weight(0.9f)
                    .fillMaxSize()
            ) {
                val screenHeight = LocalConfiguration.current.screenHeightDp.dp
                val imageHeightFraction = 0.1f
                val imageHeight = (screenHeight * imageHeightFraction)
                CustomShapeCard(
                    modifier = Modifier.padding(top = imageHeight / 2),
                    circleRadius = 16.dp,
                    cornerSize = CornerSize(32.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .height(IntrinsicSize.Max)
                            .background(Color.White)
                            .padding(16.dp)
                            .padding(top = (imageHeight / 2)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Congratulations!",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                modifier = Modifier.padding(top = 16.dp),
                                text = "You redeemed 100,000 pts for",
                                style = typography.bodyMedium
                            )

                            Row(
                                modifier = Modifier.padding(top = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "EGP ", style = MaterialTheme.typography.bodySmall)
                                Text(
                                    text = "350 ",
                                    style = typography.headlineMedium
                                )
                                Text(text = "cashback", style = MaterialTheme.typography.bodyMedium)

                            }

                        }

                        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                        Canvas(
                            Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(1.dp)

                        ) {
                            drawLine(
                                color = Color.Red,
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                pathEffect = pathEffect
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "Your remaining points 500 pts ",
                                style = typography.bodySmall
                            )
                            Text(
                                modifier = Modifier.padding(top = 16.dp),
                                text = "Cashback will added to your account ",
                                style = typography.bodySmall
                            )
                            Text(
                                modifier = Modifier.padding(top = 8.dp),
                                text = "*****174849",
                                style = typography.bodyMedium
                            )
                            Text(
                                modifier = Modifier
                                    .padding(vertical = 16.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.LightGray)
                                    .padding(8.dp),
                                text = "Amount will be added within 3 working days ",
                                fontSize = 16.sp,
                            )

                        }


                    }
                }
                Image(
                    modifier = Modifier
                        .height(imageHeight)
                        .aspectRatio(1f)
                        .align(Alignment.TopCenter)
                        .halfCircleShadow(
                            color = Color.LightGray,
                            blurRadius = 16.dp,
                        )
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(16.dp),
                    painter = painterResource(R.drawable.drump),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }

        }


}

@Preview(showBackground = true, showSystemUi = true)

@Composable
fun TicketShapeScreenPreview() {
    TicketShapeScreen()
}


/*

@Preview(name = "NEXUS_7", device = Devices.NEXUS_7,showSystemUi = true)
@Preview(name = "NEXUS_7_2013", device = Devices.NEXUS_7_2013,showSystemUi = true)
@Preview(name = "NEXUS_6", device = Devices.NEXUS_6,showSystemUi = true)
@Preview(name = "NEXUS_9", device = Devices.NEXUS_9,showSystemUi = true)
@Preview(name = "NEXUS_10", device = Devices.NEXUS_10,showSystemUi = true)
@Preview(name = "NEXUS_5X", device = Devices.NEXUS_5X,showSystemUi = true)
@Preview(name = "NEXUS_6P", device = Devices.NEXUS_6P,showSystemUi = true)
@Preview(name = "PIXEL_C", device = Devices.PIXEL_C,showSystemUi = true)
@Preview(name = "PIXEL_2_XL", device = Devices.PIXEL_2_XL,showSystemUi = true)
@Preview(name = "PIXEL_3", device = Devices.PIXEL_3,showSystemUi = true)
@Preview(name = "PIXEL_4_XL", device = Devices.PIXEL_4_XL,showSystemUi = true)
 */





