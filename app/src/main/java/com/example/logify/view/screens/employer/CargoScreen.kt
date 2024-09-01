package com.example.logify.view.screens.employer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.logify.data.Cargo
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.Black
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.GreenStatus
import com.example.logify.view.components.CargoList
import com.example.logify.view.components.CustomSearchField
import com.example.logify.view.components.DriverBottomAppBarWithBadge
import com.example.logify.view.components.EmployerBottomAppBar
import com.example.logify.viewmodel.CargoViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CargoScreen() {
    var dummySearch = ""
//    val cargos by cargoViewModel.cargos.observeAsState(emptyList())
//        var search by remember { mutableStateOf("") }
//    var filteredItems by remember { mutableStateOf(cargos) }
//
//    val unreadMessageCount by cargoViewModel.unreadMessageCount.collectAsState(0)
//
//    LaunchedEffect(search) {
//        filteredItems = if (search.isEmpty()) {
//            cargos
//        } else {
//            cargos.filter {
//                it.id.toString().contains(search) || it.status.contains(
//                    search,
//                    ignoreCase = true
//                )
//            }
//        }
//    }
//
//    LaunchedEffect(Unit) {
////        cargoViewModel.fetchUnreadMessageCount(chatId)
//    }
//
//    LaunchedEffect(Unit) {
//        cargoViewModel.loadCargos(employerId)
//    }

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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(BlueBar)
                                    .padding(horizontal = 16.dp, vertical = 20.dp),
                                contentAlignment = Alignment.BottomCenter
                            ) {
//                                CustomSearchField(
//                                    value = search,
//                                    onValueChange = { search = it },
//                                    placeholder = "Search"
//                                )
                                CustomSearchField(
                                    value = dummySearch,
                                    onValueChange = { dummySearch = it },
                                    placeholder = "Search"
                                )
                            }
                        },
                        modifier = Modifier.height(120.dp)
                    )
                },
                bottomBar = {
                    EmployerBottomAppBar(unreadMessageCount = 1)
                },
                floatingActionButton = {
                    AddButton() // Adding the button to the screen
                },
                floatingActionButtonPosition = FabPosition.End,
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundLightBlue)
                    .imePadding()
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundLightBlue)
                        .padding(innerPadding)
                ) {
//                    CargoList(cargoItems = filteredItems)
                }
            }
        }
    }
}

@Composable
fun AddButton(modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = { /* Handle click */ },
        containerColor = GreenStatus,
        contentColor = Color.White,
        modifier = modifier
            .shadow(5.dp, CircleShape, clip = true)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Preview
@Composable
fun PreviewCargoScreen(){
    CargoScreen()
}