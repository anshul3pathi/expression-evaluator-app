package com.example.expressionevaluator.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ExpressionEvaluationResponse(
    @SerializedName("result")
    @Expose
    val evaluatedExpressions: List<String>?,

    @SerializedName("error")
    @Expose
    val error: String?
)
