package com.example.expressionevaluator.domain.models

import com.example.expressionevaluator.data.local.dto.ExpressionAndEvaluationEntity

data class ExpressionAndEvaluation(
    val id: Long,
    val expression: String,
    val evaluation: String,
    val timeOfCreationInMillis: Long
)

fun ExpressionAndEvaluation.toExpressionAndEvaluationEntity(): ExpressionAndEvaluationEntity {
    return ExpressionAndEvaluationEntity(
        id = this.id,
        expression = this.expression,
        evaluatedResult = this.evaluation,
        timeStampInLong = this.timeOfCreationInMillis
    )
}

