package org.christopher_two.squid_game_desktop.di

import org.christopher_two.squid_game_desktop.ui.screens.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ViewModelModule = module {
    viewModelOf(::HomeViewModel)
}