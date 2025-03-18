package org.christopher_two.squid_game_desktop.ui.components.game

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun Lights() {
    var isActivated by remember { mutableStateOf(true) }

    // Animaciones de color
    val colorGreen by animateColorAsState(
        targetValue = if (!isActivated) Color.Green else Color.Gray,
        label = "colorGreen"
    )
    val colorRed by animateColorAsState(
        targetValue = if (isActivated) Color.Red else Color.Gray,
        label = "colorRed"
    )

    // Efecto para cambiar el estado cada segundo
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // Espera 1 segundo
            isActivated = !isActivated // Cambia el estado
        }
    }

    // Diseño de las luces
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        content = {
            Spacer(modifier = Modifier.weight(1f))
            Light(isActivated = !isActivated, color = colorGreen) // Luz verde
            Spacer(modifier = Modifier.weight(1f))
            Light(isActivated = isActivated, color = colorRed) // Luz roja
            Spacer(modifier = Modifier.weight(1f))
        }
    )
}

@Composable
fun Light(isActivated: Boolean, color: Color) {
    Canvas(modifier = Modifier.size(100.dp)) {
        drawCircle(
            color = if (isActivated) color else Color.Gray,
            radius = 100f,
            center = Offset(x = 100f, y = 100f)
        )
        drawCircle(
            color = Color.Black.copy(alpha = 0.5f),
            radius = 50f,
            center = Offset(x = 100f, y = 100f)
        )
    }
}