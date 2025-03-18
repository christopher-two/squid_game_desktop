package org.christopher_two.squid_game_desktop

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.christopher_two.squid_game_desktop.di.initKoin
import org.christopher_two.squid_game_desktop.ui.theme.TitleFont
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.KoinContext
import squid_game_desktop.composeapp.generated.resources.Res
import squid_game_desktop.composeapp.generated.resources.change_history_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import squid_game_desktop.composeapp.generated.resources.circle_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import squid_game_desktop.composeapp.generated.resources.crop_square_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24

fun main() = application {
    val windowState = rememberWindowState(
        width = 1280.dp,
        height = 720.dp,
        placement = WindowPlacement.Maximized
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = "Squid Game",
        resizable = true,
        undecorated = true,
        state = windowState,
        content = {
            initKoin()
            KoinContext {
                Column(
                    modifier = Modifier.fillMaxSize().background(Color.Black),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    WindowDraggable(
                        windowState = windowState,
                        exitApplication = ::exitApplication,
                        frameWindowScope = this@Window
                    )
                    App()
                }
            }
        }
    )
}

@Composable
fun WindowDraggable(
    windowState: WindowState,
    exitApplication: () -> Unit,
    frameWindowScope: FrameWindowScope,
) {
    frameWindowScope.WindowDraggableArea {
        Row(
            Modifier
                .fillMaxWidth()
                .height(38.dp)
                .padding(start = 8.dp)
                .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Squid Game ")
                    addStyle(
                        style = SpanStyle(
                            fontSize = 40.sp,
                            color = Color(0xFFed1b76),
                        ),
                        start = 1,
                        end = 2
                    )
                    addStyle(
                        style = SpanStyle(
                            fontSize = 40.sp,
                            color = Color(0xFFed1b76),
                        ),
                        start = 7,
                        end = 8
                    )
                    addStyle(
                        style = SpanStyle(
                            color = Color(0xFFed1b76),
                            fontSize = 40.sp,
                            fontWeight = FontWeight.ExtraBold
                        ),
                        start = 9,
                        end = 10
                    )
                },
                color = Color.White,
                fontSize = 40.sp,
                modifier = Modifier.padding(start = 8.dp),
                style = TitleFont()
            )
            ActionButtons(
                exitApplication = exitApplication,
                windowState = windowState
            )
        }
    }
}

@Composable
fun ActionButtons(
    exitApplication: () -> Unit,
    windowState: WindowState,
) {
    val hoverMaximize = remember { MutableInteractionSource() }
    val isHoveredMaximize by hoverMaximize.collectIsHoveredAsState()
    val colorHoverMaximize by animateColorAsState(
        targetValue = if (isHoveredMaximize) Color(0xFFed1b76) else Color.White,
        animationSpec = tween(durationMillis = 600)
    )

    val hoverMinimize = remember { MutableInteractionSource() }
    val isHoveredMinimize by hoverMinimize.collectIsHoveredAsState()
    val colorHoverMinimize by animateColorAsState(
        targetValue = if (isHoveredMinimize) Color(0xFF249f9c) else Color.White,
        animationSpec = tween(durationMillis = 600)
    )

    val hoverClose = remember { MutableInteractionSource() }
    val isHoveredClose by hoverClose.collectIsHoveredAsState()
    val colorHoverClose by animateColorAsState(
        targetValue = if (isHoveredClose) Color.Red else Color.White,
        animationSpec = tween(durationMillis = 600)
    )

    Row {
        IconButton(
            onClick = {
                windowState.isMinimized = true
            },
            content = {
                Icon(
                    painter = painterResource(Res.drawable.circle_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24),
                    contentDescription = "Minimize",
                    modifier = Modifier.height(30.dp).hoverable(hoverMinimize),
                    tint = colorHoverMinimize
                )
            }
        )
        IconButton(
            onClick = {
                windowState.placement =
                    if (windowState.placement == WindowPlacement.Maximized)
                        WindowPlacement.Floating
                    else
                        WindowPlacement.Maximized
            },
            content = {
                Icon(
                    painter = painterResource(Res.drawable.change_history_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24),
                    contentDescription = "Maximize",
                    modifier = Modifier.height(30.dp).hoverable(hoverMaximize),
                    tint = colorHoverMaximize
                )
            }
        )
        IconButton(
            onClick = { exitApplication() },
            content = {
                Icon(
                    painter = painterResource(Res.drawable.crop_square_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24),
                    contentDescription = "Close",
                    modifier = Modifier.height(30.dp).hoverable(hoverClose),
                    tint = colorHoverClose
                )
            }
        )
    }
}