package com.example.logify.view.screens.employer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logify.R
import com.example.logify.data.User
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.Pal24SemiW
import com.example.logify.view.components.AddFloatingButton
import com.example.logify.view.components.DriverList
import com.example.logify.view.components.EmployerBottomAppBar
import com.google.accompanist.insets.ProvideWindowInsets


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EDriversScreen() {
//    val drivers by driverViewModel.drivers.observeAsState(emptyList())
    val drivers by remember { mutableStateOf(listOf<User>(User(1, "Name", "Surname", "+484832843824", "Driver"), User(1, "Name", "Surname", "+484832843824", "Driver"), User(1, "Name", "Surname", "+484832843824", "Driver"))) }

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
                                ) {
                                    Text(text = "Drivers", style = Pal24SemiW)
                                }
                            }
                        }
                    )
                },
                floatingActionButton = {
                    AddFloatingButton(imageVector = null, idOfDrawable = R.drawable.add_user) // Adding the button to the screen
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
                    Image(
                        painter = painterResource(id = R.drawable.complex_background_cropped),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                    Column {
                        Spacer(modifier = Modifier.height(20.dp))
                        DriverList(driverItems = drivers, false)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDrivers(){
    EDriversScreen()
}