package com.example.expressionevaluator.data.repository

import com.example.expressionevaluator.data.remote.EvaluationApi
import com.example.expressionevaluator.data.remote.dto.ExpressionEvaluationRequest
import com.example.expressionevaluator.domain.models.ExpressionAndEvaluation
import com.example.expressionevaluator.domain.repository.EvaluationRepository
import javax.inject.Inject

class EvaluationRepositoryImpl @Inject constructor(
    private val api: EvaluationApi
) : EvaluationRepository {

    override suspend fun evaluateExpressions(expressions: List<String>): Result<List<ExpressionAndEvaluation>> {
        return try {
            val result = api.getEvaluatedExpressions(
                ExpressionEvaluationRequest(expressions)
            )

            Result.success(
                expressions.zip(result.evaluatedExpressions ?: emptyList()) { expression, evaluatedResult ->
                    ExpressionAndEvaluation(
                        expression = expression,
                        evaluation = evaluatedResult
                    )
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}