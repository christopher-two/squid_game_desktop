package org.christopher_two.squid_game_desktop.di

import com.network.firebase.di.firebaseModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration? = null) =
    startKoin {
        appDeclaration?.invoke(this)
        modules(
            appModule,
            ViewModelModule,
            firebaseModule
        )
    }

