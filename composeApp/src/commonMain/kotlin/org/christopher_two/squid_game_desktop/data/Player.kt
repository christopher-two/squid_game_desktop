package org.christopher_two.squid_game_desktop.data

data class Player(
    val name: String,
    val avatar: String,
    val years: String,
    val debt: String,
    val number: String,
    var isAlive: Boolean,
)