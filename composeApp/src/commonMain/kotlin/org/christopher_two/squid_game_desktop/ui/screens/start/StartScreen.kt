package org.christopher_two.squid_game_desktop.ui.screens.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.christopher_two.squid_game_desktop.ui.components.LogoCanvas
import org.christopher_two.squid_game_desktop.utils.routes.RoutesStart

@Composable
fun StartScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
    ) {
        LogoCanvas(
            modifier = Modifier
                .align(Alignment.Center)
                .size(600.dp),
            navController = {
                navController.navigate(RoutesStart.Home.route)
            }
        )
    }
}