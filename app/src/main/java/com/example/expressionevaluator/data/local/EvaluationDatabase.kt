package com.example.expressionevaluator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.expressionevaluator.data.local.dto.ExpressionAndEvaluationEntity

@Database(
    entities = [ExpressionAndEvaluationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EvaluationDatabase : RoomDatabase() {

    abstract val dao: ExpressionsDao
}