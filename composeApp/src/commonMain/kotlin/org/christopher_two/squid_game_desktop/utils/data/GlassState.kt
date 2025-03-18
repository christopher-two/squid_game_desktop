package org.christopher_two.squid_game_desktop.utils.data

import org.christopher_two.squid_game_desktop.data.Player

data class GlassState(
    val left: Glass?,
    val right: Glass?,
)

data class Glass(
    val player: Player?,
    val isCorrect: Boolean,
)