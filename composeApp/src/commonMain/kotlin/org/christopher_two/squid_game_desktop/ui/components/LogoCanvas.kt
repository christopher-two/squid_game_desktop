package org.christopher_two.squid_game_desktop.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp

@Composable
fun LogoCanvas(
    navController: () -> Unit,
    modifier: Modifier = Modifier
        .size(width = 200.dp, height = 400.dp)
        .background(Color.Black)
) {
    var isClicked by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isClicked, label = "logoTransition")

    val circleColor = Color.White
    // Animaciones para el círculo superior
    val topCircleAlpha by transition.animateFloat(
        transitionSpec = { tween(300) },
        label = "topCircleAlpha"
    ) { if (it) 0f else 1f }

    val topCircleScale by transition.animateFloat(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) },
        label = "topCircleScale"
    ) { if (it) 0.5f else 1f }

    // Animaciones para el triángulo
    val triangleColor by transition.animateColor(
        transitionSpec = { tween(500) },
        label = "triangleColor"
    ) { if (it) Color.White else Color(0xFFFF0080) }

    // Animaciones para el cuadrado
    val squareColor by transition.animateColor(
        transitionSpec = { tween(700) },
        label = "squareColor"
    ) { if (it) Color.White else Color(0xFFFF0080) }

    val triangleOffsetY by transition.animateDp(
        transitionSpec = { tween(500) },
        label = "squareOffset"
    ) { if (it) 100.dp else 0.dp }

    val squareOffsetX by transition.animateDp(
        transitionSpec = { tween(500) },
        label = "squareOffset"
    ) { if (it) 260.dp else 0.dp }
    val squareOffsetY by transition.animateDp(
        transitionSpec = { tween(500) },
        label = "squareOffset"
    ) { if (it) (-210).dp else 0.dp }

    val circleOffsetX by transition.animateDp(
        transitionSpec = { spring(dampingRatio = Spring.DampingRatioMediumBouncy) },
        label = "circleOffset"
    ) { if (it) (-150).dp else 0.dp }

    val circleOffsetY by transition.animateDp(
        transitionSpec = { spring(dampingRatio = Spring.DampingRatioMediumBouncy) },
        label = "circleOffset"
    ) { if (it) (-280).dp else 0.dp }

    val circleScale by transition.animateFloat(
        transitionSpec = { tween(500, easing = FastOutSlowInEasing) },
        label = "circleScale"
    ) { if (it) 1.9f else 1f }

    Canvas(
        modifier = modifier
            .clickable {
                if (isClicked) {
                    navController()
                }
                isClicked = !isClicked
            }
    ) {
        val circleRadiusPx = 50.dp.toPx()
        val strokeWidthPx = 5.dp.toPx()
        val centerX = size.width / 2
        val centerY = size.height / 2
        val halfWidth = size.width / 2
        val spacing = 50.dp // Margen entre figuras

        val topCircleCenter = Offset(
            x = halfWidth,
            y = circleRadiusPx // un poco por debajo del borde superior
        )

        // Círculo superior (con fade out y escala)
        drawCircle(
            color = Color.White.copy(alpha = topCircleAlpha),
            radius = circleRadiusPx * topCircleScale,
            center = topCircleCenter,
            style = Stroke(strokeWidthPx)
        )

        val triangleTopY = topCircleCenter.y + circleRadiusPx - spacing.toPx() + triangleOffsetY.toPx()
        val triangleHeight = 200.dp.toPx()
        val triangleWidth = 200.dp.toPx()

        // Triángulo (animación completa)
        Path().apply {
            // Vértice superior en el centro horizontal
            moveTo(halfWidth, triangleTopY)
            // Esquina inferior izquierda
            lineTo(halfWidth - triangleWidth / 2, triangleTopY + triangleHeight)
            // Esquina inferior derecha
            lineTo(halfWidth + triangleWidth / 2, triangleTopY + triangleHeight)
            // Cerrar el path
            close()
        }.let {
            drawPath(
                path = it,
                color = triangleColor,
                style = Stroke(strokeWidthPx),
            )
        }

        // Cuadrado (desplazamiento derecho + color)

        // El cuadrado empieza justo donde acaba el triángulo
        val squareTopY = triangleTopY + triangleHeight + 10.dp.toPx()
        val squareSize = 200.dp.toPx()

        drawRect(
            color = squareColor,
            topLeft = Offset(
                x = halfWidth - squareSize / 2 + squareOffsetX.toPx(),
                y = squareTopY + squareOffsetY.toPx()
            ),
            size = Size(200.dp.toPx(), 200.dp.toPx()),
            style = Stroke(strokeWidthPx)
        )

        // Círculo inferior (desplazamiento izquierdo + color)
        val bottomCircleCenter = Offset(
            x = halfWidth + circleOffsetX.toPx(),
            y = squareTopY + squareSize + circleRadiusPx - spacing.toPx() + circleOffsetY.toPx()
        )
        withTransform({
            scale(circleScale, pivot = Offset(centerX, centerY))
        }) {
            drawCircle(
                color = circleColor,
                radius = circleRadiusPx,
                center = bottomCircleCenter,
                style = Stroke(strokeWidthPx)
            )
        }
    }
}