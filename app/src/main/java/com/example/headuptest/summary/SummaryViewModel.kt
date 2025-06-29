package com.example.headuptest.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headuptest.summary.domain.repository.SummaryRepository
import com.example.headuptest.util.calculateCarbs
import com.example.headuptest.util.calculateFats
import com.example.headuptest.util.calculateProtein
import com.example.headuptest.util.getCalories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class SummaryViewModel @Inject constructor(
    val repository: SummaryRepository,
) : ViewModel() {

    private val state = MutableStateFlow<SummaryState>(initState())

    fun observeState() = state.asStateFlow()

    fun onAction(action: Action) {
        when (action) {
            is Action.Init -> {
                viewModelScope.launch {
                    repository.getEntities().collect { diaryList ->
                        val params = repository.getParameters()
                        val allProteins = diaryList.sumOf { it.proteins }
                        val allCarbs = diaryList.sumOf { it.carbs }
                        val allFats = diaryList.sumOf { it.fats }
                        val allKcal = diaryList.sumOf { it.calories }

                        val proteinProgress: Double = params?.calculateProtein()?.let { allProteins / it } ?: 0.toDouble()
                        val carbsProgress: Double = params?.calculateCarbs()?.let { allCarbs / it } ?: 0.toDouble()
                        val fatsProgress: Double = params?.calculateFats()?.let { allFats / it } ?: 0.toDouble()
                        val caloriesProgress: Double = params?.getCalories()?.let { allKcal / it } ?: 0.toDouble()

                        state.update {
                            it.copy(
                                protein = SummaryItem(
                                    "$allProteins/${params?.calculateProtein()?.roundToInt() ?: 0} G",
                                    proteinProgress.toFloat()
                                ),
                                carbs = SummaryItem(
                                    "$allCarbs/${params?.calculateCarbs()?.roundToInt() ?: 0} G",
                                    carbsProgress.toFloat()
                                ),
                                fats = SummaryItem(
                                    "$allFats/${params?.calculateFats()?.roundToInt() ?: 0} G",
                                    fatsProgress.toFloat()
                                ),
                                calories = SummaryItem(
                                    "$allKcal/${params?.getCalories()?.roundToInt() ?: 0} KCAL",
                                    caloriesProgress.toFloat()
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun initState() = SummaryState(
        protein = SummaryItem("", 0f),
        carbs = SummaryItem("", 0f),
        fats = SummaryItem("", 0f),
        calories = SummaryItem("", 0f),
    )

    data class SummaryState(
        val protein: SummaryItem,
        val carbs: SummaryItem,
        val fats: SummaryItem,
        val calories: SummaryItem,
    )

    data class SummaryItem(
        val value: String,
        val progress: Float,
    )

    sealed interface Action {
        data object Init : Action
    }
}