package com.example.expressionevaluator.data.repository

import com.example.expressionevaluator.data.local.ExpressionsDao
import com.example.expressionevaluator.data.local.dto.toExpressionAndEvaluation
import com.example.expressionevaluator.data.remote.EvaluationApi
import com.example.expressionevaluator.data.remote.dto.ExpressionEvaluationRequest
import com.example.expressionevaluator.data.remote.dto.ExpressionEvaluationResponse
import com.example.expressionevaluator.domain.models.ExpressionAndEvaluation
import com.example.expressionevaluator.domain.models.toExpressionAndEvaluationEntity
import com.example.expressionevaluator.domain.repository.EvaluationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.math.exp

class EvaluationRepositoryImpl @Inject constructor(
    private val api: EvaluationApi,
    private val dao: ExpressionsDao

) : EvaluationRepository {

    override suspend fun evaluateExpressions(expressions: List<String>): Result<List<ExpressionAndEvaluation>> {
        return try {
            val result = api.getEvaluatedExpressions(ExpressionEvaluationRequest(expressions))

            val expressionsAndEvaluations = expressions.zip(result.evaluatedExpressions ?: emptyList()) { expression, evaluatedResult ->
                ExpressionAndEvaluation(
                    id = 0L,
                    expression = expression,
                    evaluation = evaluatedResult,
                    timeOfCreationInMillis = System.currentTimeMillis()
                )
            }

            val idsOfSavedExpressions = saveExpressionsInDB(expressionsAndEvaluations)

            Result.success(
                expressionsAndEvaluations.mapIndexed { index, expressionAndEvaluation ->
                    expressionAndEvaluation.copy(id = idsOfSavedExpressions[index])
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getAllExpressions(): Flow<List<ExpressionAndEvaluation>> {
        return dao.getAllExpressions().map { expressionAndEvaluationEntities ->
            expressionAndEvaluationEntities.map { it.toExpressionAndEvaluation() }
        }
    }

    private suspend fun saveExpressionsInDB(expressionsAndEvaluations: List<ExpressionAndEvaluation>): List<Long> {
        return dao.insertExpressions(
            expressionsAndEvaluations.map { it.toExpressionAndEvaluationEntity() }
        )
    }
}