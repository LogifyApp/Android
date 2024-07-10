package com.example.logify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logify.data.Cargo
import com.example.logify.ui.theme.LogifyTheme
import com.example.logify.view.screens.CargoScreen
import com.example.logify.view.screens.InitialScreen
import com.example.logify.view.screens.LoginScreen
import com.example.logify.view.screens.RegisterScreen
import com.example.logify.viewmodel.TokenViewModel
import com.example.logify.viewmodel.UserViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private val tokenViewModel: TokenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            LogifyTheme {
                val systemUiController = rememberSystemUiController()
                val navController = rememberNavController()
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                    )
                }
                AppContent(navController, userViewModel)
            }
        }
    }

    @Composable
    fun AppContent(navController: NavHostController, viewModel: UserViewModel) {
        NavHost(navController = navController, startDestination = "cargo") {
            composable("initial") { InitialScreen(navController) }
            composable("login") { LoginScreen(navController, viewModel) }
            composable("register") { RegisterScreen(navController, viewModel) }
            composable("cargo") { CargoScreen(cargoItems = getCargoItems()) }
        }
    }

    private fun getCargoItems(): List<Cargo> {
        return listOf(
            Cargo(id = 31023, status = "Created", creationDate = "4.05.2024", carId = 1, driverId = 1),
            Cargo(id = 31024, status = "Started", creationDate = "4.05.2024", carId = 1, driverId = 1),
            Cargo(id = 31023, status = "In-check", creationDate = "4.05.2024", carId = 1, driverId = 1),
            Cargo(id = 31023, status = "Finished", creationDate = "4.05.2024", carId = 1, driverId = 1),
            Cargo(id = 31023, status = "Problem", creationDate = "4.05.2024", carId = 1, driverId = 1)
        )
    }
}