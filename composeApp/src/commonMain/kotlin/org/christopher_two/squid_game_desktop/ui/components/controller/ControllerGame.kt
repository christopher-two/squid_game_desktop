package org.christopher_two.squid_game_desktop.ui.components.controller

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.christopher_two.squid_game_desktop.ui.components.Widget
import squid_game_desktop.composeapp.generated.resources.Res
import squid_game_desktop.composeapp.generated.resources.*

@Composable
fun ControllerGame() {
    Box(
        modifier = Modifier.fillMaxWidth().height(300.dp),
        contentAlignment = Alignment.Center,
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Widget(
                        info = "Start",
                        onClick = { /*TODO*/ },
                        modifier = Modifier.width(700.dp).height(100.dp),
                        color = Color(0xFF41cd19),
                        isSelect = false,
                        icon = Res.drawable.play_arrow_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Widget(
                        info = "Kill Player",
                        onClick = { /*TODO*/ },
                        modifier = Modifier.width(700.dp).height(100.dp),
                        color = Color(0xFFa71922),
                        isSelect = false,
                        icon = Res.drawable.skull_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Widget(
                        info = "Pause",
                        onClick = { /*TODO*/ },
                        modifier = Modifier.width(700.dp).height(100.dp),
                        color = Color(0xFFffde12),
                        isSelect = false,
                        icon = Res.drawable.pause_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Widget(
                        info = "Restart",
                        onClick = { /*TODO*/ },
                        modifier = Modifier.width(700.dp).height(100.dp),
                        color = Color(0xFF905adf),
                        isSelect = false,
                        icon = Res.drawable.restart_alt_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    )
}