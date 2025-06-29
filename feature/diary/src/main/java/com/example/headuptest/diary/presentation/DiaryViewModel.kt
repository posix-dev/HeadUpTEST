package com.example.headuptest.diary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headuptest.diary.shared.DiaryRepository
import com.example.headuptest.model.Entity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val repository: DiaryRepository,
) : ViewModel() {

    private val state = MutableStateFlow<DiaryState>(initState())

    fun observeState() = state.asStateFlow()

    fun onAction(action: Action) {
        when (action) {
            is Action.Init -> {
                viewModelScope.launch {
                    repository.getAllEntities().collect {
                        state.update { oldState ->
                            oldState.copy(items = it)
                        }
                    }
                }
            }
        }
    }

    private fun initState() = DiaryState(
        items = listOf()
    )

    data class DiaryState(
        val items: List<Entity>,
    )

    sealed interface Action {
        data object Init : Action

    }
}