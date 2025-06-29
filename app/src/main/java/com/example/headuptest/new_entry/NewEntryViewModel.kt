package com.example.headuptest.new_entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headuptest.new_entry.data.NewEntryRepository
import com.example.headuptest.new_entry.domain.entity.Entity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewEntryViewModel @Inject constructor(
    private val repository: NewEntryRepository,
) : ViewModel() {

    private val state = MutableStateFlow<NewEntryState>(initState())
    private val events = MutableSharedFlow<NewEntryEvent>()

    fun observeState() = state.asStateFlow()

    fun observeEvents() = events.asSharedFlow()

    fun onAction(action: Action) {
        when (action) {
            is Action.UpdateName -> state.update {
                it.copy(name = action.name, isNameValidated = true)
            }

            is Action.UpdateCarbs -> state.update {
                val carbs = action.carbs.filter { it.isDigit() }
                it.copy(carbs = carbs, isCarbsValidated = true)
            }

            is Action.UpdateProteins -> state.update {
                val proteins = action.proteins.filter { it.isDigit() }
                it.copy(proteins = proteins, isProteinsValidated = true)
            }

            is Action.UpdateFats -> state.update {
                val fats = action.fats.filter { it.isDigit() }
                it.copy(fats = fats, isFatsValidated = true)
            }

            Action.InsertEntry -> {
                val isNameValidated = state.value.name.isNotEmpty()
                val isCarbsValidated = state.value.carbs.isNotEmpty()
                val isProteinsValidated = state.value.proteins.isNotEmpty()
                val isFatsValidated = state.value.fats.isNotEmpty()

                val isAnyError = listOf(
                    isNameValidated, isCarbsValidated, isProteinsValidated, isFatsValidated
                ).any { !it }

                if (isAnyError) {
                    state.update {
                        it.copy(
                            isNameValidated = isNameValidated,
                            isCarbsValidated = isCarbsValidated,
                            isProteinsValidated = isProteinsValidated,
                            isFatsValidated = isFatsValidated,
                        )
                    }
                } else {
                    viewModelScope.launch {
                        state.value.apply {
                            val carbs = carbs.toIntOrNull() ?: 0
                            val proteins = proteins.toIntOrNull() ?: 0
                            val fats = fats.toIntOrNull() ?: 0

                            repository.insertEntry(
                                Entity(
                                    name = name,
                                    carbs = carbs,
                                    proteins = proteins,
                                    fats = fats,
                                    calories = calculateCalories(carbs, proteins, fats),
                                )
                            )
                        }
                        events.emit(NewEntryEvent.GoBack)
                    }
                }
            }
        }
    }

    private fun calculateCalories(carbs: Int, proteins: Int, fats: Int): Int {
        val calories = (proteins * 4) + (carbs * 4) + (fats * 9)
        return calories
    }

    private fun initState() = NewEntryState(
        name = "",
        carbs = "",
        proteins = "",
        fats = "",
        calories = "",
        isNameValidated = true,
        isCarbsValidated = true,
        isProteinsValidated = true,
        isFatsValidated = true,
    )

    data class NewEntryState(
        val name: String,
        val carbs: String,
        val calories: String,
        val proteins: String,
        val fats: String,
        val isNameValidated: Boolean,
        val isCarbsValidated: Boolean,
        val isProteinsValidated: Boolean,
        val isFatsValidated: Boolean,
    )

    sealed interface Action {

        data class UpdateName(
            val name: String,
        ) : Action

        data class UpdateCarbs(
            val carbs: String,
        ) : Action

        data class UpdateProteins(
            val proteins: String,
        ) : Action

        data class UpdateFats(
            val fats: String,
        ) : Action

        data object InsertEntry : Action
    }

    sealed interface NewEntryEvent {
        data object GoBack: NewEntryEvent
    }
}