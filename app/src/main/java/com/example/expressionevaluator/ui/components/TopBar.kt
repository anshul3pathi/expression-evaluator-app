package com.example.expressionevaluator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expressionevaluator.ui.theme.LocalSpacing

@Composable
fun ExpressionEvaluatorAppTopBar(
    titleText: String,
    modifier: Modifier = Modifier,
    titleTextFontSize: TextUnit = 28.sp,
    titleTextFontColor: Color = MaterialTheme.colorScheme.onPrimary,
    navigationIcon: (@Composable () -> Unit)? = null
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        navigationIcon?.let {
            navigationIcon()
        }

        Text(
            text = titleText,
            fontSize = titleTextFontSize,
            color = titleTextFontColor,
            modifier = Modifier.padding(horizontal = if (navigationIcon != null) 0.dp else spacing.spaceMedium)
        )
    }
}