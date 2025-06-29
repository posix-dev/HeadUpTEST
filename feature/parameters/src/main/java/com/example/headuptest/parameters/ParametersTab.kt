package com.example.headuptest.parameters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.example.headuptest.ui.R as CR

object ParametersTab: Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.parameters_tab)
            val icon = painterResource(CR.drawable.ic_parameters_24)

            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(ParametersScreen()) { navigator: Navigator ->
            SlideTransition(navigator)
        }
    }
}