package org.christopher_two.squid_game_desktop.utils.routes

sealed class RoutesGames(val route: String) {
    data object RedLightGreenLight : RoutesGames("Red Light Green Light")
    data object DalgonaCandyChallenge : RoutesGames("Dalgona Candy Challenge")
    data object TugofWar : RoutesGames("Tug of War")
    data object Marbles: RoutesGames("Marbles")
    data object GlassBridge : RoutesGames("Glass Bridge")
    data object SquidGame: RoutesGames("Squid Game")
    data object Rally: RoutesGames("Rally")
}