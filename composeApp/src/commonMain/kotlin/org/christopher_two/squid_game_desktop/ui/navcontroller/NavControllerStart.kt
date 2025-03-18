package org.christopher_two.squid_game_desktop.ui.navcontroller

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.christopher_two.squid_game_desktop.ui.screens.home.HomeScreen
import org.christopher_two.squid_game_desktop.ui.screens.start.StartScreen
import org.christopher_two.squid_game_desktop.utils.routes.RoutesStart

@Composable
fun NavControllerStart() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RoutesStart.Start.route,
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        enterTransition = { fadeIn(tween(1500)) }
    ) {
        composable(RoutesStart.Start.route) { StartScreen(navController) }
        composable(RoutesStart.Home.route) { HomeScreen() }
    }
}