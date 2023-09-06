package com.example.expressionevaluator.ui.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expressionevaluator.domain.repository.EvaluationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HistoryScreenViewModel @Inject constructor(
    private val repository: EvaluationRepository
) : ViewModel() {

    val state = repository.getAllExpressions()
        .map { expressionsAndEvaluations ->
            val expressionsStrings = expressionsAndEvaluations
                .sortedByDescending { it.timeOfCreationInMillis }
                .map {
                    "${it.expression} => ${it.evaluation}"
                }
            HistoryScreenState(expressionsStrings)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HistoryScreenState()
        )
}