package org.christopher_two.squid_game_desktop.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.christopher_two.squid_game_desktop.ui.components.controller.ControllerGame
import org.christopher_two.squid_game_desktop.ui.components.controller.ControllerSelectionGame
import org.christopher_two.squid_game_desktop.ui.screens.home.HomeViewModel
import org.christopher_two.squid_game_desktop.utils.data.HomeState

@Composable
fun Controller(state: HomeState, viewModel: HomeViewModel, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(10.dp)
            .border(
                width = 1.dp,
                color = colorScheme.onSecondary.copy(alpha = 0.3f),
                shape = RoundedCornerShape(16.dp)
            ),
        content = {
            ControllerSelectionGame(
                state = state,
                viewModel = viewModel,
                navController = navController
            )
            ControllerGame()
        }
    )
}