package com.example.logify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logify.di.AppModule
import com.example.logify.ui.theme.LogifyTheme
import com.example.logify.view.screens.InitialScreen
import com.example.logify.view.screens.LoginScreen
import com.example.logify.view.screens.RegisterScreen
import com.example.logify.viewmodel.UserViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val userRepository = AppModule.provideUserRepository(applicationContext)
//        val userViewModel = UserViewModel(userRepository)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            LogifyTheme {
                val systemUiController = rememberSystemUiController()
                val isLightTheme = MaterialTheme.colorScheme.background.luminance() > 0.5

                val navController = rememberNavController()

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = isLightTheme
                    )
                }
//                AppContent(navController, userViewModel)
                AppContent(navController)
            }
        }
    }

    @Composable
//    fun AppContent(navController: NavHostController, viewModel: UserViewModel) {
    fun AppContent(navController: NavHostController) {
        NavHost(navController = navController, startDestination = "initial") {
            composable("initial") { InitialScreen(navController) }
            composable("login") { LoginScreen(navController) }
//            composable("login") { LoginScreen(navController, viewModel) }
            composable("register") { RegisterScreen(navController) }
//            composable("register") { RegisterScreen(navController, viewModel) }
        }
    }
}