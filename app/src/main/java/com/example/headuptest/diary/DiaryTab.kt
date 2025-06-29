package com.example.headuptest.diary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.example.headuptest.R

object DiaryTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.diary_tab)
            val icon = painterResource(R.drawable.ic_diary_24)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(DiaryScreen()) { navigator: Navigator ->
            SlideTransition(navigator)
        }
    }
}