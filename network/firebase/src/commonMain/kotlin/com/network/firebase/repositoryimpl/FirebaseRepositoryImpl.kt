package com.network.firebase.repositoryimpl

import com.network.firebase.models.StatusPlayer
import com.network.firebase.repository.FirebaseRepository
import kotlinx.coroutines.flow.Flow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class FirebaseRepositoryImpl(): FirebaseRepository {
    override fun getPlayers(): Flow<List<StatusPlayer>>
    override fun getServos(): Flow<List<Boolean>>
    override suspend fun updateServo(index: Int, status: Boolean)
    override suspend fun killPlayer(id: String)
    override suspend fun savePlayer(id: String, isAlive: Boolean)
    override suspend fun winnerPlayer(id: String, isWinner: Boolean)
}