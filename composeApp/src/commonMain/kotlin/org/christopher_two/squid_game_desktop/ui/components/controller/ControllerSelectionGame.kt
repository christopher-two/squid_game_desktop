package org.christopher_two.squid_game_desktop.ui.components.controller

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.christopher_two.squid_game_desktop.ui.components.Widget
import org.christopher_two.squid_game_desktop.ui.screens.home.HomeViewModel
import org.christopher_two.squid_game_desktop.utils.data.HomeState
import kotlin.math.max
import kotlin.math.min

@Composable
fun ControllerSelectionGame(
    state: HomeState,
    viewModel: HomeViewModel,
    navController: NavController
) {
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val itemCount = state.games.size

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                state = lazyListState,
                modifier = Modifier
                    .padding(10.dp)
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures { _, dragAmount ->
                            coroutineScope.launch {
                                when {
                                    dragAmount > 50 -> { // Arrastre hacia la derecha
                                        val prevIndex = max(
                                            lazyListState.firstVisibleItemIndex - 1,
                                            0
                                        )
                                        lazyListState.animateScrollToItem(prevIndex)
                                    }

                                    dragAmount < -50 -> { // Arrastre hacia la izquierda
                                        val nextIndex = min(
                                            lazyListState.firstVisibleItemIndex + 1,
                                            itemCount - 1
                                        )
                                        lazyListState.animateScrollToItem(nextIndex)
                                    }
                                }
                            }
                        }
                    }
            ) {
                items(state.games) { game ->
                    Widget(
                        info = game.title,
                        icon = game.icon,
                        color = game.color,
                        isSelect = state.selectedGame == game,
                        onClick = {
                            viewModel.update { copy(selectedGame = game) }
                            navController.navigate(game.route)
                        },
                        modifier = Modifier
                            .height(200.dp)
                            .width(300.dp)
                            .padding(horizontal = 10.dp)
                    )
                }
            }
            ButtonsLazy(
                lazyListState = lazyListState,
                coroutineScope = coroutineScope,
                itemCount = itemCount
            )
        }
    )
}

@Composable
private fun ButtonsLazy(
    lazyListState: LazyListState,
    coroutineScope: CoroutineScope,
    itemCount: Int
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        content = {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        val prevIndex = max(lazyListState.firstVisibleItemIndex - 1, 0)
                        lazyListState.animateScrollToItem(prevIndex)
                    }
                },
                content = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = colorScheme.onSecondary,
                        modifier = Modifier.fillMaxSize()
                    )
                },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(80.dp)
                    .padding(10.dp)
            )
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        val nextIndex = min(
                            lazyListState.firstVisibleItemIndex + 1,
                            itemCount - 1
                        )
                        lazyListState.animateScrollToItem(nextIndex)
                    }
                },
                content = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = colorScheme.onSecondary,
                        modifier = Modifier.fillMaxSize()
                    )
                },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(80.dp)
                    .padding(10.dp)
            )
        }
    )
}