package com.example.expressionevaluator.data.remote

import com.example.expressionevaluator.data.remote.dto.ExpressionEvaluationRequest
import com.example.expressionevaluator.data.remote.dto.ExpressionEvaluationResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface EvaluationApi {


    @POST("/v4/")
    suspend fun getEvaluatedExpressions(
        @Body body: ExpressionEvaluationRequest,
    ): ExpressionEvaluationResponse

    companion object {
        const val BASE_URL = "http://api.mathjs.org"
    }
}