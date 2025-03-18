package org.christopher_two.squid_game_desktop.ui.screens.home


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.christopher_two.squid_game_desktop.utils.data.HomeState

class HomeViewModel: ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun update(update: HomeState.() -> HomeState) {
        _state.value = update(_state.value)
    }
}