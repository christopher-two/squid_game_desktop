package org.christopher_two.squid_game_desktop.utils.data

import com.network.firebase.models.StatusPlayer
import org.christopher_two.squid_game_desktop.ui.components.game.GlassState
import org.christopher_two.squid_game_desktop.utils.enums.Games
import kotlin.enums.EnumEntries

data class HomeState(
    var isLoading: Boolean = false,
    val games: EnumEntries<Games> = Games.entries,
    var selectedGame: Games = Games.RED_LIGHT_GREEN_LIGHT,
    val statusPlayers: List<StatusPlayer>? = null,
    val rewards: String = "",
    val glasses: List<GlassState>? = null
)