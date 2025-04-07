package org.christopher_two.squid_game_desktop.ui.components.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.network.firebase.models.StatusPlayer
import org.christopher_two.squid_game_desktop.ui.screens.home.HomeViewModel
import org.christopher_two.squid_game_desktop.utils.data.HomeState

// 1. Añadir estado y manejo de clics
@Composable
fun Glasses(
    viewModel: HomeViewModel,
    state: HomeState
) {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        state.glasses?.let { glassList ->
            // Agrupar los vidrios en pares
            val groupedGlasses = glassList.chunked(2)

            items(groupedGlasses) { pair ->
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(120.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Primer vidrio del par
                    pair.getOrNull(0)?.let { glass ->
                        Glass(
                            player = glass.left,
                            isActive = glass.isActive,
                            onClick = { viewModel.toggleGlass(glass.id) },
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Segundo vidrio del par
                    pair.getOrNull(1)?.let { glass ->
                        Glass(
                            player = glass.left,
                            isActive = glass.isActive,
                            onClick = { viewModel.toggleGlass(glass.id) },
                        )
                    }
                }
            }
        } ?: run {
            item {
                CircularProgressIndicator()
            }
        }
    }
}
// 2. Modificar el componente Glass
@Composable
fun Glass(
    player: StatusPlayer?,
    isActive: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clickable(onClick = onClick) // Añadir modificador clickable
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        if (isActive) Color.Red else Color.White.copy(alpha = 0.66f),
                        if (isActive) Color.DarkGray else Color.White.copy(alpha = 0.33f)
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 2.dp,
                color = if (isActive) Color.Yellow else Color.White.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        // ... contenido existente
    }
}

// 4. Actualizar data class
data class GlassState(
    val id: Int,
    val isActive: Boolean,
    val left: StatusPlayer?,
    val right: StatusPlayer?
)