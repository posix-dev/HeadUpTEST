package com.example.headuptest.new_entry.nav

import cafe.adriel.voyager.core.registry.screenModule
import com.example.headuptest.nav.SharedScreen
import com.example.headuptest.new_entry.NewEntryScreen

val featureNewEntryScreenModule = screenModule {
    register<SharedScreen.NewEntryScreen> { provider ->
        NewEntryScreen()
    }
}