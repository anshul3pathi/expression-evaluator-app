package com.example.expressionevaluator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expressionevaluator.ui.navigation.Routes
import com.example.expressionevaluator.ui.screens.evaluation.EvaluationScreenRoute
import com.example.expressionevaluator.ui.screens.history.HistoryScreenRoute
import com.example.expressionevaluator.ui.theme.ExpressionEvaluatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpressionEvaluatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
private fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.EVALUATION_SCREEN) {
        composable(route = Routes.EVALUATION_SCREEN) {
            EvaluationScreenRoute(navController = navController)
        }

        composable(route = Routes.HISTORY_SCREEN) {
            HistoryScreenRoute(navController = navController)
        }
    }
}