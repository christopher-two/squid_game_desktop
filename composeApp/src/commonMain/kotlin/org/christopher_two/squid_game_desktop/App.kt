package org.christopher_two.squid_game_desktop

import androidx.compose.runtime.Composable
import org.christopher_two.squid_game_desktop.ui.navcontroller.NavControllerStart
import org.christopher_two.squid_game_desktop.ui.theme.MaterialThemeApp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialThemeApp {
        NavControllerStart()
    }
}