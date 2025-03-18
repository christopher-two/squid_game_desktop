package org.christopher_two.squid_game_desktop.utils.routes

sealed class RoutesStart(val route: String) {
    data object Start: RoutesStart("Start")
    data object Home: RoutesStart("Home")
}