package com.example.expressionevaluator.ui.screens.evaluation

sealed class EvaluationScreenState {
    object Loading : EvaluationScreenState()

    data class Success(val expression: String = "") :  EvaluationScreenState()

    data class Error(val errorMessage: String? = null) : EvaluationScreenState()
}
