package org.christopher_two.squid_game_desktop.ui.components.game

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Marbles() {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(5),
        verticalItemSpacing = 10.dp,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp),
    ) {
        items(100) {
            MarblesBox()
        }
    }
}

@Composable
fun MarblesBox(color: Color = Color.White) {
    val hover = remember { MutableInteractionSource() }
    val isHovered by hover.collectIsHoveredAsState()
    val colorHover by animateColorAsState(
        targetValue = if (isHovered) color.copy(alpha = 0.1f) else Color.Transparent,
        animationSpec = tween(durationMillis = 600)
    )
    val colorHoverText by animateColorAsState(
        targetValue = if (isHovered) Color.White else color,
        animationSpec = tween(durationMillis = 600)
    )
    Box(
        modifier = Modifier
            .height(100.dp)
            .width(50.dp)
            .hoverable(hover)
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
        content = {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = colorHoverText
                )
                Text(
                    text = "10",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = colorHoverText
                )
                Text(
                    text = "Marbles",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    color = colorHoverText
                )
            }
        }
    )
}