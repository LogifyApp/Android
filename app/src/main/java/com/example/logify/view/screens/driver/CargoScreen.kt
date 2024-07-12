package com.example.logify.view.screens.driver

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.logify.R
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.DarkBlue
import com.example.logify.view.components.CargoList
import com.example.logify.view.components.CustomSearchField
import com.example.logify.view.components.DriverBottomAppBarWithBadge
import com.example.logify.viewmodel.CargoViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CargoScreen(cargoViewModel: CargoViewModel, employerId: Int, chatId: Int,
                lastChatOpenDateTime: LocalDateTime) {
    val cargos by cargoViewModel.cargos.observeAsState(emptyList())
    var search by remember { mutableStateOf("") }
    var filteredItems by remember { mutableStateOf(cargos) }

    val unreadMessageCount by cargoViewModel.unreadMessageCount.collectAsState(0)

    LaunchedEffect(search) {
        filteredItems = if (search.isEmpty()) {
            cargos
        } else {
            cargos.filter {
                it.id.toString().contains(search) || it.status.contains(
                    search,
                    ignoreCase = true
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        cargoViewModel.fetchUnreadMessageCount(chatId)
    }

    LaunchedEffect(Unit) {
        cargoViewModel.loadCargos(employerId)
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(BlueBar)
                                    .padding(horizontal = 16.dp, vertical = 16.dp),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                CustomSearchField(
                                    value = search,
                                    onValueChange = { search = it },
                                    placeholder = "Search"
                                )
                            }
                        },
                        modifier = Modifier.height(130.dp)
                    )
                },
                bottomBar = {
                    DriverBottomAppBarWithBadge(unreadMessageCount = unreadMessageCount)
                },
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
                    CargoList(cargoItems = filteredItems)
                }
            }
        }
    }
}