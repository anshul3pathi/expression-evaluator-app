package com.example.expressionevaluator.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expressionevaluator.core.utils.Constants
import com.example.expressionevaluator.domain.models.ExpressionAndEvaluation

@Entity(
    tableName = Constants.DATABASE_NAME
)
data class ExpressionAndEvaluationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val expression: String,
    val evaluatedResult: String,
    val timeStampInLong: Long = System.currentTimeMillis()
)

fun ExpressionAndEvaluationEntity.toExpressionAndEvaluation(): ExpressionAndEvaluation {
    return ExpressionAndEvaluation(
        id = this.id,
        expression = this.expression,
        evaluation = this.evaluatedResult,
        timeOfCreationInMillis = this.timeStampInLong
    )
}
