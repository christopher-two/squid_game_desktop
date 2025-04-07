package com.network.firebase.repositoryimpl

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.FirestoreOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.FirebaseDatabase
import java.io.File
import java.io.FileInputStream

object FirebaseAdmin {
    private lateinit var firestoreInstance: Firestore
    private lateinit var databaseInstance: FirebaseDatabase

    fun initialize() {
        try {
            // 1. Cargar credenciales
            val serviceAccount = FileInputStream(
                File("C:\\Users\\chris\\Desktop\\horus-d67b2-firebase-adminsdk-ozpwm-79c4d64ce2.json")
            )

            // 2. Configurar opciones para Realtime Database
            val databaseOptions = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://horus-d67b2-default-rtdb.firebaseio.com/")
                .build()

            // 3. Inicializar app de Firebase
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(databaseOptions)
            }

            // 4. Configurar Firestore
            val firestoreOptions = FirestoreOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(FileInputStream(
                    File("C:\\Users\\chris\\Desktop\\horus-d67b2-firebase-adminsdk-ozpwm-79c4d64ce2.json")
                )))
                .build()

            // 5. Inicializar instancias
            databaseInstance = FirebaseDatabase.getInstance()
            firestoreInstance = firestoreOptions.service

        } catch (e: Exception) {
            throw IllegalStateException("Error initializing Firebase: ${e.message}", e)
        }
    }

    fun getDatabase(): FirebaseDatabase {
        if (!::databaseInstance.isInitialized) {
            throw IllegalStateException("Firebase Database not initialized")
        }
        return databaseInstance
    }

    fun getFirestore(): Firestore {
        if (!::firestoreInstance.isInitialized) {
            throw IllegalStateException("Firestore not initialized")
        }
        return firestoreInstance
    }
}