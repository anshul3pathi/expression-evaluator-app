package com.example.expressionevaluator.domain.repository

import com.example.expressionevaluator.domain.models.ExpressionAndEvaluation

interface EvaluationRepository {
    suspend fun evaluateExpressions(expressions: List<String>): Result<List<ExpressionAndEvaluation>>
}