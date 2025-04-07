package com.network.firebase.di

import com.network.firebase.repository.FirebaseRepository
import com.network.firebase.repositoryimpl.FirebaseRepositoryImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val firebaseModule: Module
    get() = module {
        factoryOf(::FirebaseRepositoryImpl).bind(FirebaseRepository::class)
    }