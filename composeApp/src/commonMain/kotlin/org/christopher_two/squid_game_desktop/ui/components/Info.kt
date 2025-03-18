package org.christopher_two.squid_game_desktop.ui.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import squid_game_desktop.composeapp.generated.resources.Res
import squid_game_desktop.composeapp.generated.resources.paid_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import squid_game_desktop.composeapp.generated.resources.sentiment_satisfied_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24

@Composable
fun Info(totalPlayers: Int, rewards: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().align(Alignment.TopStart),
        ) {
            info(
                info = "Players",
                content = totalPlayers.toString(),
                modifier = Modifier.fillMaxWidth(.5f),
                icon = Res.drawable.sentiment_satisfied_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24,
                color = MaterialTheme.colorScheme.onSecondary
            )
            Spacer(modifier = Modifier.size(10.dp))
            info(
                info = "Rewards",
                content = rewards,
                modifier = Modifier.fillMaxWidth(),
                icon = Res.drawable.paid_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

@Composable
fun info(
    info: String,
    content: String,
    modifier: Modifier,
    icon: DrawableResource,
    color: Color
) {
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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .height(200.dp)
            .padding(top = 10.dp)
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
            )
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = info,
            color = colorHoverText,
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = content,
            color = colorHoverText,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = colorHoverText,
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}