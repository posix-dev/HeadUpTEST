package com.example.headuptest

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.example.headuptest.new_entry.nav.featureNewEntryScreenModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            featureNewEntryScreenModule()
        }
    }
}