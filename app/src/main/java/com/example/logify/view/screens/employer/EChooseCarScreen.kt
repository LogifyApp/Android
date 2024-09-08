package com.example.logify.view.screens.employer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logify.R
import com.example.logify.data.Car
import com.example.logify.data.User
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.BlueBar
import com.example.logify.view.components.CarList
import com.example.logify.view.components.EmployerBottomAppBar
import com.google.accompanist.insets.ProvideWindowInsets


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EChooseCarScreen() {
//    val cars by carViewModel.cars.observeAsState(emptyList())
    val cars by remember { mutableStateOf(listOf<Car>(
        Car("C3JN6C", "Ducatto", "Renault", true, 1),
        Car("C31NAC", "Ducatto", "Renault", true, 1),
        Car("CNA75C", "Ducatto", "Renault", false, 1),
        Car("CN3A6C", "Ducatto", "Renault", false, 1)))
    }
    ProvideWindowInsets {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLightBlue)
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = BlueBar
                        ),
                        title = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.offset((-25).dp)
                                ) {
                                    Text(text = "Add new cargo", color = Color.White)
                                }
                            }
                        },
                        navigationIcon = {
                            IconButton(onClick = { /* Handle navigation back */ }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                            }
                        }
                    )
                },
                bottomBar = {
                    EmployerBottomAppBar(unreadMessageCount = 1)
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundLightBlue)
                        .padding(innerPadding)
                ) {
                    Column {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp), contentAlignment = Alignment.Center){
                            Text(text = "Choose car from the list", fontSize = 24.sp,
                                style = TextStyle(fontFamily = FontFamily(Font(R.font.palanquin_bold))))
                        }
                        HorizontalDivider(color = BlueBar, thickness = 1.dp)
                        Spacer(modifier = Modifier.height(16.dp))
                        CarList(carItems = cars)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewChooseCar(){
    EChooseCarScreen()
}