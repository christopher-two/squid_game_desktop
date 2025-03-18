package org.christopher_two.squid_game_desktop.ui.components.controller

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.christopher_two.squid_game_desktop.data.Player
import org.christopher_two.squid_game_desktop.utils.data.HomeState

@Composable
fun Players(state: HomeState) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(10.dp),
        content = {
            items(state.players) {
                PlayerBox(player = it)
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    )
}

@Composable
fun PlayerBox(player: Player) {
    val color = if (player.isAlive) colorScheme.onSecondary else Color.Red
    val hover = remember { MutableInteractionSource() }
    val isHovered by hover.collectIsHoveredAsState()
    val colorHover by animateColorAsState(
        targetValue = if (isHovered) color else Color.Transparent,
        animationSpec = tween(durationMillis = 600)
    )
    val colorHoverText by animateColorAsState(
        targetValue = if (isHovered) Color.White else color,
        animationSpec = tween(durationMillis = 600)
    )

    Box(
        modifier = Modifier
            .hoverable(hover)
            .fillMaxWidth()
            .height(100.dp)
            .border(
                width = 1.dp,
                color = color.copy(alpha = 0.3f),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        color.copy(alpha = 0.1f),
                        colorHover,
                        colorHover,
                        colorHover,
                        colorHover
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.CenterStart,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxSize(),
                content = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "",
                        modifier = Modifier.size(50.dp),
                        tint = colorHoverText
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = "${player.name} ${player.number}",
                            color = colorHoverText,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = if (player.isAlive) "Active" else "Deleted",
                            color = colorHoverText,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            )
        }
    )
}