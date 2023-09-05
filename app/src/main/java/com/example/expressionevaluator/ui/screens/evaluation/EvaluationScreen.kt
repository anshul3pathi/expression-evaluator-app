package com.example.expressionevaluator.ui.screens.evaluation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expressionevaluator.R
import com.example.expressionevaluator.ui.theme.ExpressionEvaluatorTheme

@Composable
fun EvaluationScreenRoute(
    viewModel: EvaluationScreenViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    when (state) {
        is EvaluationScreenState.Error -> {
            Toast.makeText(
                context,
                "Something went wrong! - ${(state as EvaluationScreenState.Error).errorMessage}",
                Toast.LENGTH_LONG
            ).show()
        }

        EvaluationScreenState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is EvaluationScreenState.Success -> {
            val expression = (state as EvaluationScreenState.Success).expression
            EvaluationScreen(
                expression = expression,
                onChangeExpression = viewModel::onChangeExpression,
                onClickEvaluateButton = viewModel::onEvaluateExpression
            )
        }
    }

}

@Composable
private fun EvaluationScreen(
    expression: String,
    onChangeExpression: (String) -> Unit,
    onClickEvaluateButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(bottom = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            value = expression,
            onValueChange = onChangeExpression,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )

        Button(onClick = onClickEvaluateButton) {
            Text(text = stringResource(id = R.string.lbl_evaluate), fontSize = 14.sp)
        }
    }
}

@Preview
@Composable
fun EvaluationScreen_Preview() {
    ExpressionEvaluatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            EvaluationScreen(
                expression = "a*b+c",
                onChangeExpression = {},
                onClickEvaluateButton = {}
            )
        }
    }
}