package com.network.firebase.repositoryimpl

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.network.firebase.models.StatusPlayer
import com.network.firebase.repository.FirebaseRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class FirebaseRepositoryImpl actual constructor() : FirebaseRepository {
    actual override fun getPlayers(): Flow<List<StatusPlayer>> = callbackFlow {
        val playersRef = FirebaseAdmin.getDatabase().getReference("players")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val players = mutableListOf<StatusPlayer>()

                    for (playerSnapshot in snapshot.children) {
                        // Extraer todos los campos manualmente
                        val isAlive =
                            playerSnapshot.child("alive").getValue(Boolean::class.java) ?: false
                        val isWinner =
                            playerSnapshot.child("winner").getValue(Boolean::class.java) ?: false
                        val isActive =
                            playerSnapshot.child("active").getValue(Boolean::class.java) ?: false
                        val image = playerSnapshot.child("image").getValue(String::class.java) ?: ""
                        val numPlayer = playerSnapshot.child("numPlayer").getValue(String::class.java) ?: ""

                        players.add(
                            StatusPlayer(
                                isAlive = isAlive,
                                isWinner = isWinner,
                                isActive = isActive,
                                id = playerSnapshot.key ?: "",
                                sex = "sex",
                                fullName = "fullName",
                                height = "height",
                                weight = "weight",
                                city = "city",
                                country = "country",
                                financialHistory = "financialHistory",
                                age = "age",
                                civilStatus = "civilStatus",
                                numPlayer = numPlayer,
                                gender = "gender",
                                image = image
                            )
                        )
                    }
                    trySend(players).isSuccess
                } catch (e: Exception) {
                    close(e)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                close(Exception(error.toException()))
            }
        }

        playersRef.addValueEventListener(listener)
        awaitClose { playersRef.removeEventListener(listener) }
    }

    actual override fun getServos(): Flow<List<Boolean>> = callbackFlow {
        val servosRef = FirebaseAdmin.getDatabase().getReference("Servos")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val servosList = mutableListOf<Boolean>()

                    // Iterar sobre cada servo en orden numérico
                    for (i in 0 until 16) { // 16 servos como en tu JSON
                        val servoValue = snapshot.child(i.toString()).getValue(String::class.java)
                        servosList.add(servoValue?.toBoolean() == true)
                    }

                    trySend(servosList).isSuccess
                } catch (e: Exception) {
                    close(e)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                close(Exception(error.toException()))
            }
        }

        servosRef.addValueEventListener(listener)
        awaitClose { servosRef.removeEventListener(listener) }
    }

    actual override suspend fun updateServo(index: Int, status: Boolean) {
        try {
            val ref = FirebaseAdmin.getDatabase().getReference("Servos/$index")
            ref.setValueAsync(status.toString())
        } catch (e: Exception) {
            throw Exception("Error actualizando servo: ${e.message}")
        }
    }

    actual override suspend fun killPlayer(id: String) {
        try {
            val ref = FirebaseAdmin.getDatabase().getReference("players/$id")
            ref.child("alive").setValueAsync(false)
        } catch (e: Exception) {
            throw Exception("Error actualizando jugador: ${e.message}")
        }
    }

    actual override suspend fun savePlayer(id: String, isAlive: Boolean) {
        try {
            val ref = FirebaseAdmin.getDatabase().getReference("players/${id}")
            ref.child("alive").setValueAsync(isAlive)
        } catch (e: Exception) {
            throw Exception("Error actualizando jugador: ${e.message}")
        }
    }

    actual override suspend fun winnerPlayer(id: String, isWinner: Boolean) {
        try {
            val ref = FirebaseAdmin.getDatabase().getReference("players/$id")
            ref.child("winner").setValueAsync(isWinner)
        } catch (e: Exception) {
            throw Exception("Error actualizando jugador: ${e.message}")
        }
    }
}