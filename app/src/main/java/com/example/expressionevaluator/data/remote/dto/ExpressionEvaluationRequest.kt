package com.example.expressionevaluator.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ExpressionEvaluationRequest(
    @SerializedName("expr")
    @Expose
    val expressions: List<String>,

    @SerializedName("precision")
    @Expose
    val precision: Int? = null
)
