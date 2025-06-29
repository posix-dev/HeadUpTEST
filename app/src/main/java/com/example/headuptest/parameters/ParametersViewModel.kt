package com.example.headuptest.parameters

import androidx.lifecycle.ViewModel
import com.example.headuptest.parameters.domain.ParametersRepository
import com.example.headuptest.parameters.domain.entity.Parameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ParametersViewModel @Inject constructor(
    private val repository: ParametersRepository,
) : ViewModel() {

    private val state = MutableStateFlow<ParametersState>(initState())

    fun observeState() = state.asStateFlow()

    fun onAction(action: Action) {
        when (action) {
            is Action.Init -> {
                val params = repository.getParameters()

                if (params != null) {
                    state.update {
                        it.copy(
                            height = params.height.toString(),
                            weight = params.weight.toString(),
                            age = params.age.toString(),
                        )
                    }
                }
            }

            is Action.UpdateAge -> state.update {
                val age = action.age.filter { it.isDigit() }
                it.copy(age = age)
            }

            is Action.UpdateHeight -> state.update {
                val height = action.height.filter { it.isDigit() }
                it.copy(height = height)
            }

            is Action.UpdateWeight -> state.update {
                it.copy(weight = action.weight)
            }

            Action.UpdateData -> {
                val weight = state.value.weight.replace(',', '.').toDoubleOrNull()

                repository.saveParameters(
                    Parameters(
                        height = state.value.height.toIntOrNull() ?: 0,
                        weight = weight ?: 0.toDouble(),
                        age = state.value.age.toIntOrNull() ?: 0
                    )
                )
            }
        }
    }

    private fun initState() = ParametersState(
        height = "",
        weight = "",
        age = ""
    )

    data class ParametersState(
        val height: String,
        val weight: String,
        val age: String,
    )

    sealed interface Action {
        data object Init: Action

        data class UpdateHeight(
            val height: String,
        ) : Action

        data class UpdateWeight(
            val weight: String,
        ) : Action

        data class UpdateAge(
            val age: String,
        ) : Action

        data object UpdateData : Action
    }
}