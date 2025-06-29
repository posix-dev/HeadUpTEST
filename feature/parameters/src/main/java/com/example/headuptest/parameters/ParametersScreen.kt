package com.example.headuptest.parameters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.headuptest.ui.CustomTextField
import com.example.headuptest.ui.R as CR

class ParametersScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = hiltViewModel<ParametersViewModel>()
        val state by viewModel.observeState().collectAsState()

        LaunchedEffect(Unit) {
            viewModel.onAction(ParametersViewModel.Action.Init)
        }

        DisposableEffect(Unit) {
            onDispose {
                viewModel.onAction(ParametersViewModel.Action.UpdateData)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = stringResource(R.string.parameters_title),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.displaySmall
            )
            CustomTextField(
                Modifier.padding(bottom = 16.dp),
                stringResource(R.string.parameters_height),
                stringResource(R.string.parameters_height_desc),
                CR.drawable.ic_vice_versa_vertical_24,
                state.height
            ) {
                viewModel.onAction(
                    ParametersViewModel.Action.UpdateHeight(it
                    )
                )
            }
            CustomTextField(
                Modifier.padding(bottom = 16.dp),
                stringResource(R.string.parameters_weight),
                stringResource(R.string.parameters_weight_desc),
                CR.drawable.ic_vice_versa_horizontal_24,
                state.weight
            ) {
                viewModel.onAction(
                    ParametersViewModel.Action.UpdateWeight(it)
                )
            }
            CustomTextField(
                Modifier.padding(bottom = 16.dp),
                stringResource(R.string.parameters_age),
                stringResource(R.string.parameters_age_desc),
                CR.drawable.ic_person_24,
                state.age
            ) {
                viewModel.onAction(
                    ParametersViewModel.Action.UpdateAge(
                        it
                    )
                )
            }
        }
    }
}