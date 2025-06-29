package com.example.headuptest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.headuptest.diary.presentation.DiaryTab
import com.example.headuptest.nav.TabNavigationItem
import com.example.headuptest.parameters.ParametersTab
import com.example.headuptest.summary.SummaryTab
import com.example.headuptest.ui.theme.HeadUpTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HeadUpTestTheme {
                MainRoute()
            }
        }
    }
}

@Composable
private fun MainRoute() {
    TabNavigator(SummaryTab) {
        Scaffold(
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    CurrentTab()
                }
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ) {
                    TabNavigationItem(SummaryTab)
                    TabNavigationItem(DiaryTab)
                    TabNavigationItem(ParametersTab)
                }
            }
        )
    }
}