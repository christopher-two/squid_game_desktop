package org.christopher_two.squid_game_desktop.ui.components.game

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun TugOfWarGame() {
    val color = MaterialTheme.colorScheme.onSecondary
    // Estado para la posición del terreno (entre -1 y 1)
    var terrainPosition by remember { mutableStateOf(0f) }

    // Efecto para simular el combate infinito
    LaunchedEffect(Unit) {
        while (true) {
            delay(100) // Intervalo de actualización
            // Mueve el terreno aleatoriamente hacia un lado u otro
            terrainPosition += (Random.nextFloat() - 0.5f) * 0.1f
            // Limita el terreno para que no llegue a los extremos
            terrainPosition = terrainPosition.coerceIn(-0.9f, 0.9f)
        }
    }

    // Diseño del juego
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Equipo 1
        Text(
            text = "Equipo 1",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            fontFamily = FontFamily.Monospace
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Barra de progreso y línea central
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.matchParentSize()) {
                drawRect(color = Color(0xffffffff))
            }

            Canvas(modifier = Modifier.matchParentSize()) {
                val terrainWidth = size.width * 0.4f // Ancho del terreno
                val terrainOffset = (size.width / 2) + (terrainPosition * (size.width / 2))

                drawRect(
                    color = color,
                    topLeft = Offset(terrainOffset - (terrainWidth / 2), 0f),
                    size = androidx.compose.ui.geometry.Size(terrainWidth, size.height)
                )
            }

            Canvas(modifier = Modifier.matchParentSize()) {
                drawLine(
                    color = Color.Black,
                    start = Offset(size.width / 2, 0f),
                    end = Offset(size.width / 2, size.height),
                    strokeWidth = 4f
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Equipo 2
        Text(
            text = "Equipo 2",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            fontFamily = FontFamily.Monospace
        )
    }
}