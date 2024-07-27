package com.example.pizza

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.halfCircleShadow(
    color: Color = Color.Black,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
) = then(
    drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }
            frameworkPaint.color = color.toArgb()
            val centerX = size.width / 2 + offsetX.toPx()
            val centerY = size.height / 2 + offsetY.toPx()
            val radius = size.height / 2
            val startAngle = 0f
            val sweepAngle = 180f
            canvas.drawArc(
                left = centerX - radius,
                top = centerY - radius,
                right = centerX + radius,
                bottom = centerY + radius,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                paint = paint
            )
        }
    }
)

fun Modifier.drawTicketShadow(
    shape: TicketShape,
    shadowColor: Color = Color.LightGray,
    elevation: Dp = 2.dp,
): Modifier = this.drawBehind {
    drawIntoCanvas { canvas ->
        val shadowPath = shape.getPath(size, Density(density))

        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        paint.color = shadowColor
        paint.isAntiAlias = true

        val blurMaskFilter = BlurMaskFilter(elevation.toPx(), BlurMaskFilter.Blur.NORMAL)
        frameworkPaint.maskFilter = blurMaskFilter
        val shadowOffsetX = if (elevation > 0.dp) elevation.toPx() else 0f
        val shadowOffsetY = if (elevation > 0.dp) elevation.toPx() else 0f

        canvas.save()

        if (elevation > 0.dp) {
            canvas.translate(-shadowOffsetX, shadowOffsetY)
            canvas.drawPath(shadowPath, paint)
        }
        canvas.restore()

        canvas.save()

        if (elevation > 0.dp) {
            canvas.translate(shadowOffsetX, shadowOffsetY)
            canvas.drawPath(shadowPath, paint)
        }
        canvas.restore()

        canvas.save()

        if (elevation > 0.dp) {
            canvas.translate(0f, shadowOffsetY)
            canvas.drawPath(shadowPath, paint)
        }
        canvas.restore()
    }
}