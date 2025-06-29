package com.example.headuptest.new_entry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.headuptest.ui.CustomTextField
import com.example.headuptest.ui.R as CR

class NewEntryScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel = hiltViewModel<NewEntryViewModel>()
        val state by viewModel.observeState().collectAsState()

        LaunchedEffect(Unit) {
            viewModel.observeEvents().collect {
                when(it) {
                    is NewEntryViewModel.NewEntryEvent.GoBack -> navigator?.pop()
                }
            }
        }

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primaryContainer,
                    onClick = {
                        viewModel.onAction(NewEntryViewModel.Action.InsertEntry)
                    }) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            },
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable {
                                navigator?.pop()
                            },
                        painter = painterResource(CR.drawable.ic_close_24),
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
                    CR.drawable.ic_letter_24,
                    state.name,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                    ),
                    isError = !state.isNameValidated,
                    onAction = {
                        viewModel.onAction(NewEntryViewModel.Action.UpdateName(it))
                    }
                )
                CustomTextField(
                    Modifier.padding(bottom = 16.dp),
                    "Carbs",
                    "The amount of carbs in this meal",
                    CR.drawable.ic_star_24,
                    state.carbs,
                    isError = !state.isCarbsValidated,
                    onAction = {
                        viewModel.onAction(NewEntryViewModel.Action.UpdateCarbs(it))
                    }
                )
                CustomTextField(
                    Modifier.padding(bottom = 16.dp),
                    "Proteins",
                    "The amount of proteins in this meal",
                    CR.drawable.ic_star_24,
                    state.proteins,
                    isError = !state.isProteinsValidated,
                    onAction = {
                        viewModel.onAction(NewEntryViewModel.Action.UpdateProteins(it))
                    }
                )
                CustomTextField(
                    Modifier.padding(bottom = 16.dp),
                    "Fats",
                    "The amount of fats in this meal",
                    CR.drawable.ic_star_24,
                    state.fats,
                    isError = !state.isFatsValidated,
                    onAction = {
                        viewModel.onAction(NewEntryViewModel.Action.UpdateFats(it))
                    }
                )
            }
        }
    }

}