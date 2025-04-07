package com.network.firebase.repository

import com.network.firebase.models.StatusPlayer
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    fun getPlayers(): Flow<List<StatusPlayer>>
    fun getServos(): Flow<List<Boolean>>
    suspend fun updateServo(index: Int, status: Boolean)
    suspend fun killPlayer(id: String)
    suspend fun savePlayer(id: String, isAlive: Boolean)
    suspend fun winnerPlayer(id: String, isWinner: Boolean)
}