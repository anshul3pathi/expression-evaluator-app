package com.example.expressionevaluator.ui.screens.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.expressionevaluator.R
import com.example.expressionevaluator.ui.components.ExpressionEvaluatorAppTopBar
import com.example.expressionevaluator.ui.theme.ExpressionEvaluatorTheme
import com.example.expressionevaluator.ui.theme.LocalSpacing

@Composable
fun HistoryScreenRoute(
    navController: NavController,
    viewModel: HistoryScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    HistoryScreen(
        evaluationItems = state.expressions,
        onClickBackButton = { navController.navigateUp() }
    )
}

@Composable
private fun HistoryScreen(
    evaluationItems: List<String>,
    onClickBackButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        EvaluationHistoryList(
            evaluationItems = evaluationItems,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 58.dp,
                    bottom = spacing.spaceMedium,
                    start = spacing.spaceMedium,
                    end = spacing.spaceMedium
                )
        )

        ExpressionEvaluatorAppTopBar(
            titleText = stringResource(id = R.string.lbl_history),
            modifier = Modifier.align(Alignment.TopCenter),
            navigationIcon = {
                IconButton(onClick = onClickBackButton) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = null
                    )
                }
            }
        )
    }
}

@Composable
private fun EvaluationHistoryList(
    evaluationItems: List<String>,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
    ) {
        items(evaluationItems) { evaluationItem ->
            Text(text = evaluationItem)
        }
    }
}

@Preview
@Composable
private fun HistoryScreen_Preview() {
    val dummyExpressions = (1..20).map { _ ->
        "1 + 2 + 3 => 6"
    }
    ExpressionEvaluatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            HistoryScreen(
                evaluationItems = dummyExpressions,
                onClickBackButton = {}
            )
        }
    }
}