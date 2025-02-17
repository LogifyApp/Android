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
import com.example.logify.data.User
import com.example.logify.ui.theme.LogifyTheme
import com.example.logify.view.screens.driver.CargoScreen
import com.example.logify.view.screens.InitialScreen
import com.example.logify.view.screens.LoginScreen
import com.example.logify.view.screens.MapScreen
import com.example.logify.view.screens.RegisterScreen
import com.example.logify.view.screens.ChatScreen
import com.example.logify.view.screens.driver.DetailedCargoScreen
import com.example.logify.view.screens.driver.ProfileScreen
import com.example.logify.view.screens.employer.EAddEditCarScreen
import com.example.logify.view.screens.employer.EAddNextPointScreen
import com.example.logify.view.screens.employer.EAddStartPointScreen
import com.example.logify.view.screens.employer.ECargoScreen
import com.example.logify.view.screens.employer.EDetailedCargoScreen
import com.example.logify.view.screens.employer.EProfileScreen
import com.example.logify.viewmodel.CargoViewModel
import com.example.logify.viewmodel.TokenViewModel
import com.example.logify.viewmodel.UserViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private val tokenViewModel: TokenViewModel by viewModels()
    private val cargoViewModel: CargoViewModel by viewModels()
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
                AppContent(navController, userViewModel, cargoViewModel)
            }
        }
    }

    @Composable
    fun AppContent(navController: NavHostController, userViewModel: UserViewModel, cargoViewModel: CargoViewModel) {
        NavHost(navController = navController, startDestination = "addEditCarScreen") {
            composable("initial") { InitialScreen(navController) }
            composable("login") { LoginScreen(navController, userViewModel) }
            composable("register") { RegisterScreen(navController, userViewModel) }
            composable("cargo") { CargoScreen(cargoViewModel, employerId = 1, chatId = 1, lastChatOpenDateTime = LocalDateTime.now()) }
            composable("detailed_cargo_screen/{cargoId}") { backStackEntry ->
                val cargoId = backStackEntry.arguments?.getString("cargoId")?.toIntOrNull()
                cargoId?.let {
                    DetailedCargoScreen(cargoId = it, cargoViewModel = cargoViewModel, chatId = 1)
                }
            }
            composable("chat") { ChatScreen() }
            composable("empCargo") { ECargoScreen() }
            composable("map") { MapScreen(User(1, "Name", "Surname", "+484832843824", "Driver")) }
            composable("empDetCargo") { EDetailedCargoScreen(cargoId = 1, chatId = 1) }
            composable("addStartPoint") { EAddStartPointScreen() }
            composable("addNextPoint") { EAddNextPointScreen() }
            composable("eProfileScreen") { EProfileScreen() }
            composable("profileScreen") { ProfileScreen() }
            composable("addEditCarScreen") { EAddEditCarScreen(currentUserId = 1, onSave = {}) }
        }
    }
}