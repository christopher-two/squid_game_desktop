package org.christopher_two.squid_game_desktop.ui.navcontroller

import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.christopher_two.squid_game_desktop.ui.components.game.Glasses
import org.christopher_two.squid_game_desktop.ui.components.game.Lights
import org.christopher_two.squid_game_desktop.ui.components.game.Marbles
import org.christopher_two.squid_game_desktop.ui.components.game.TugOfWarGame
import org.christopher_two.squid_game_desktop.ui.screens.home.HomeViewModel
import org.christopher_two.squid_game_desktop.utils.data.HomeState
import org.christopher_two.squid_game_desktop.utils.routes.RoutesGames

@Composable
fun NavControllerGames(navController: NavHostController, viewModel: HomeViewModel, state: HomeState) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = RoutesGames.RedLightGreenLight.route,
        enterTransition = { slideInHorizontally { -it } },
        exitTransition = { slideOutHorizontally { it } }
    ) {
        composable(RoutesGames.RedLightGreenLight.route) { Lights() }
        composable(RoutesGames.TugofWar.route) { TugOfWarGame() }
        composable(RoutesGames.Marbles.route) { Marbles() }
        composable(RoutesGames.GlassBridge.route) { Glasses(viewModel, state) }
        composable(RoutesGames.SquidGame.route) { }
        composable(RoutesGames.Rally.route) { }
    }
}