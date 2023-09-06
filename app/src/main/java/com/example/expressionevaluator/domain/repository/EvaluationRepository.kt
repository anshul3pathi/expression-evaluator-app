package com.example.expressionevaluator.domain.repository

import com.example.expressionevaluator.domain.models.ExpressionAndEvaluation
import kotlinx.coroutines.flow.Flow

interface EvaluationRepository {
    suspend fun evaluateExpressions(expressions: List<String>): Result<List<ExpressionAndEvaluation>>

    fun getAllExpressions(): Flow<List<ExpressionAndEvaluation>>
}