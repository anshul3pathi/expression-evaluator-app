package com.example.expressionevaluator.ui.screens.evaluation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expressionevaluator.domain.repository.EvaluationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

const val NEW_LINE_CHARACTER = "\n"

@HiltViewModel
class EvaluationScreenViewModel @Inject constructor(
    private val repository: EvaluationRepository
) : ViewModel() {

    private var expression: String = ""

    private val _state: MutableStateFlow<EvaluationScreenState> = MutableStateFlow(EvaluationScreenState.Success())
    val state = _state.asStateFlow()

    fun onChangeExpression(expression: String) {
        this.expression = expression
        _state.update { EvaluationScreenState.Success(expression) }
    }

    fun onEvaluateExpression() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { EvaluationScreenState.Loading }
            val normalizedExpression = expression.trim()
            val expressions = normalizedExpression.split(NEW_LINE_CHARACTER)
            repository.evaluateExpressions(expressions)
                .onSuccess { expressionAndEvaluations ->
                    val evaluatedResult =
                        expressionAndEvaluations.joinToString(NEW_LINE_CHARACTER) { expressionAndEvaluation ->
                            "${expressionAndEvaluation.expression} => ${expressionAndEvaluation.evaluation}"
                        }
                    _state.update { EvaluationScreenState.Success(evaluatedResult) }
                }
                .onFailure { throwable ->
                    _state.update { EvaluationScreenState.Error(throwable.localizedMessage) }
                }
        }
    }
}