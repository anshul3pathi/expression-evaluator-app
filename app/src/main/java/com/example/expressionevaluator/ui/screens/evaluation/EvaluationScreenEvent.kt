package com.example.expressionevaluator.ui.screens.evaluation

sealed class EvaluationScreenEvent {
    data class ShowToast(val toastMessage: String) : EvaluationScreenEvent()
}
