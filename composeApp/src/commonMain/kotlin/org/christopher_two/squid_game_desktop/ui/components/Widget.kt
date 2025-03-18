package org.christopher_two.squid_game_desktop.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun Widget(
    info: String,
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    color: Color,
    isSelect: Boolean = false,
    onClick: () -> Unit
) {
    val hover = remember { MutableInteractionSource() }
    val isHovered by hover.collectIsHoveredAsState()
    val colorHover by animateColorAsState(
        targetValue = if (isHovered || isSelect) color.copy(alpha = 0.1f) else Color.Transparent,
        animationSpec = tween(durationMillis = 600)
    )
    val colorHoverText by animateColorAsState(
        targetValue = if (isHovered || isSelect) Color.White else color,
        animationSpec = tween(durationMillis = 600)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .hoverable(hover)
            .pointerInput(Unit) { detectTapGestures { onClick() } }
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
            )
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = colorHoverText,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = info,
            color = colorHoverText,
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}