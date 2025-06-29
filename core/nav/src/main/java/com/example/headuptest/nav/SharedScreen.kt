package com.example.headuptest.nav

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen : ScreenProvider {
    class NewEntryScreen : SharedScreen()
}