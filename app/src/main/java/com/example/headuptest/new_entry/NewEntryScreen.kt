package com.example.headuptest.new_entry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.headuptest.R
import com.example.headuptest.ui.shared.composable.CustomTextField

class NewEntryScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        Scaffold(
            topBar = {
                Box(modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp)) {
                    Icon(
                        modifier = Modifier.padding(start = 16.dp).clickable {
                            navigator?.pop()
                        },
                        painter = painterResource(R.drawable.ic_close_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "New Entry",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                CustomTextField(
                    Modifier.padding(bottom = 16.dp),
                    "Name",
                    "The name of the food you want to log",
                    R.drawable.ic_letter_24
                )
                CustomTextField(
                    Modifier.padding(bottom = 16.dp),
                    "Carbs",
                    "The amount of carbs in this meal",
                    R.drawable.ic_star_24
                )
                CustomTextField(
                    Modifier.padding(bottom = 16.dp),
                    "Proteins",
                    "The amount of proteins in this meal",
                    R.drawable.ic_star_24
                )
                CustomTextField(
                    Modifier.padding(bottom = 16.dp),
                    "Fats",
                    "The amount of fats in this meal",
                    R.drawable.ic_star_24
                )
            }
        }
    }

}