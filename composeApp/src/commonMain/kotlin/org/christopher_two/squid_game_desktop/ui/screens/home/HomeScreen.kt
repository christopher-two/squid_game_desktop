package org.christopher_two.squid_game_desktop.ui.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import org.christopher_two.squid_game_desktop.ui.components.Controller
import org.christopher_two.squid_game_desktop.ui.components.Games
import org.christopher_two.squid_game_desktop.ui.components.Info
import org.christopher_two.squid_game_desktop.ui.components.controller.Players
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val navControllerGames = rememberNavController()
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(10.dp)
    ) {
        item {
            Info(totalPlayers = 455, rewards = "₩455,555,555")
            Spacer(modifier = Modifier.size(10.dp))
        }
        item {
            Games(navController = navControllerGames, state = state)
            Spacer(modifier = Modifier.size(10.dp))
        }
        item {
            Controller(
                state = state,
                viewModel = viewModel,
                navController = navControllerGames
            )
            Spacer(modifier = Modifier.size(10.dp))
        }
        item {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .border(
                        width = 1.dp,
                        color = colorScheme.onSecondary.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .fillMaxWidth()
                    .height(500.dp),
            ) {
                Players(state)
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}