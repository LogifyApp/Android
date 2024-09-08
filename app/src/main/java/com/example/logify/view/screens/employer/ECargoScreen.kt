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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.GreenStatus
import com.example.logify.view.components.AddFloatingButton
import com.example.logify.view.components.CustomSearchField
import com.example.logify.view.components.EmployerBottomAppBar
import com.google.accompanist.insets.ProvideWindowInsets


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ECargoScreen() {
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
                    AddFloatingButton(imageVector = Icons.Default.Add, idOfDrawable = null) // Adding the button to the screen
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

@Preview
@Composable
fun PreviewCargoScreen(){
    ECargoScreen()
}