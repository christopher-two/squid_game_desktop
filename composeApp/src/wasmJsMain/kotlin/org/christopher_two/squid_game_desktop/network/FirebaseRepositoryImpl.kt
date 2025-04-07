package org.christopher_two.squid_game_desktop.network

import kotlinx.coroutines.flow.Flow
import org.christopher_two.squid_game_desktop.data.Player
import org.christopher_two.squid_game_desktop.data.StatusPlayer

actual class FirebaseRepositoryImpl actual constructor(): FirebaseRepository{
    actual override fun getPlayers(): Flow<List<StatusPlayer>> {
        TODO("Not yet implemented")
    }

    actual override fun getPlayersFirestore(): Flow<List<Player>> {
        TODO("Not yet implemented")
    }

}