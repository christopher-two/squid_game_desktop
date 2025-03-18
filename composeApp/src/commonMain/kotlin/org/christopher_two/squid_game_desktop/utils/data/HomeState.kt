package org.christopher_two.squid_game_desktop.utils.data

import org.christopher_two.squid_game_desktop.data.Player
import org.christopher_two.squid_game_desktop.utils.enums.Games
import kotlin.enums.EnumEntries

data class HomeState(
    var isLoading: Boolean = false,
    val games: EnumEntries<Games> = Games.entries,
    var selectedGame: Games = Games.RED_LIGHT_GREEN_LIGHT,
    val players: List<Player> = listOf(
        Player("Juan Pérez", "https://example.com/avatar1.jpg", "25", "1000", "7", true),
        Player("María Gómez", "https://example.com/avatar2.jpg", "30", "500", "10", true),
        Player("Carlos López", "https://example.com/avatar3.jpg", "22", "200", "5", false),
        Player("Ana Rodríguez", "https://example.com/avatar4.jpg", "28", "1500", "12", true),
        Player("Luis Martínez", "https://example.com/avatar5.jpg", "35", "0", "3", true),
        Player("Sofía Hernández", "https://example.com/avatar6.jpg", "27", "300", "9", false),
        Player("Pedro Díaz", "https://example.com/avatar7.jpg", "32", "800", "15", true),
        Player("Laura García", "https://example.com/avatar8.jpg", "24", "1200", "8", true),
        Player("Jorge Ruiz", "https://example.com/avatar9.jpg", "29", "400", "11", false),
        Player("Marta Sánchez", "https://example.com/avatar10.jpg", "26", "600", "4", true),
        Player("Diego Álvarez", "https://example.com/avatar11.jpg", "31", "900", "14", true),
        Player("Elena Torres", "https://example.com/avatar12.jpg", "23", "100", "2", false),
        Player("Miguel Ramírez", "https://example.com/avatar13.jpg", "33", "1300", "6", true),
        Player("Lucía Flores", "https://example.com/avatar14.jpg", "34", "700", "13", true),
        Player("Oscar Cruz", "https://example.com/avatar15.jpg", "21", "50", "1", false),
        Player("Carmen Reyes", "https://example.com/avatar16.jpg", "36", "1100", "16", true),
        Player("Fernando Morales", "https://example.com/avatar17.jpg", "20", "0", "17", true),
        Player("Isabel Ortega", "https://example.com/avatar18.jpg", "37", "2000", "18", false),
        Player("Ricardo Vargas", "https://example.com/avatar19.jpg", "38", "300", "19", true),
        Player("Adriana Castro", "https://example.com/avatar20.jpg", "39", "400", "20", true)
    )
)