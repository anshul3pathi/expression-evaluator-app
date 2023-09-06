package com.example.expressionevaluator.data.local

import androidx.compose.ui.unit.Constraints
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.expressionevaluator.core.utils.Constants
import com.example.expressionevaluator.data.local.dto.ExpressionAndEvaluationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpressionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpressions(expressions: List<ExpressionAndEvaluationEntity>): List<Long>

    @Query("SELECT * FROM ${Constants.DATABASE_NAME}")
    fun getAllExpressions(): Flow<List<ExpressionAndEvaluationEntity>>
}