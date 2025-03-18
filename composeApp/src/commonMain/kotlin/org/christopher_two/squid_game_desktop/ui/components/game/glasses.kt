package org.christopher_two.squid_game_desktop.ui.components.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.christopher_two.squid_game_desktop.data.Player
import org.christopher_two.squid_game_desktop.utils.data.GlassState

val glasses = listOf(
    GlassState(left = null, right = null),
    GlassState(left = null, right = null),
    GlassState(left = null, right = null),
    GlassState(left = null, right = null),
    GlassState(left = null, right = null),
    GlassState(left = null, right = null),
    GlassState(left = null, right = null),
    GlassState(left = null, right = null),
    GlassState(left = null, right = null),
    GlassState(left = null, right = null),
)

@Composable
fun Glasses() {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        items(glasses) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(10.dp))
                Glass(it.left?.player)
                Spacer(modifier = Modifier.size(10.dp))
                Glass(it.left?.player)
                Spacer(modifier = Modifier.size(10.dp))
            }
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@Composable
fun Glass(
    player: Player?
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.66f),
                        Color.White.copy(alpha = 0.33f)
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.5f),
                        Color.Transparent
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(visible = player != null) {

        }
    }
}