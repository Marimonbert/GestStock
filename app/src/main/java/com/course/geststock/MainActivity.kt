package com.course.geststock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.course.geststock.domain.StockViewModelFactory
import com.course.geststock.stock.presentation.StockViewModel
import com.course.geststock.ui.theme.GestStockTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val application = applicationContext as MyApplication
            val viewModel: StockViewModel = viewModel(
                factory = StockViewModelFactory(application.repository)
            )

            GestStockTheme {
                MainScreen(viewModel = viewModel, navHostController = navController)
            }
        }
    }
}
