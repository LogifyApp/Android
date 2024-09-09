package com.example.logify.view.screens.driver

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logify.R
import com.example.logify.enums.CargoStatusInfo
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.Open26SemiW
import com.example.logify.ui.theme.Pal14SemiW
import com.example.logify.ui.theme.Pal24MedW
import com.example.logify.view.components.DetailedCargoRow
import com.example.logify.view.components.DriverBottomAppBarWithBadge
import com.example.logify.viewmodel.CargoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedCargoScreen(cargoId: Int, cargoViewModel: CargoViewModel = viewModel(), chatId: Int) {
    val cargo by cargoViewModel.selectedCargo.observeAsState()
    val cargoStatusInfo = CargoStatusInfo.fromStatus(cargo?.status ?: "Problem")
    //TODO val points by pointViewModel.points.observeAsState(emptyList())

    val unreadMessageCount by cargoViewModel.unreadMessageCount.collectAsState(0)

    LaunchedEffect(Unit) {
        cargo?.let { cargoViewModel.loadCargosByDriver(employerId = it.employerId, driverId = it.driverId) }
    }

    LaunchedEffect(Unit) {
        cargoViewModel.fetchUnreadMessageCount(chatId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.width(50.dp))
                        Text(
                            "№${cargo?.id}", style = Open26SemiW
                        )
                    }
                },
                actions = {
                    cargo?.let {
                        Text(
                            text = it.status, style = Pal14SemiW,
                            modifier = Modifier
                                .background(
                                    cargoStatusInfo.color,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(horizontal = 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BlueBar
                )
            )
        },
        bottomBar = {
            DriverBottomAppBarWithBadge(unreadMessageCount = unreadMessageCount)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLightBlue)
                .padding(innerPadding)
        ) {
            cargo?.let { cargo ->
                Button(
                    onClick = { /* Handle send documents */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .background(cargoStatusInfo.color, shape = RoundedCornerShape(4.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = cargoStatusInfo.color)
                ) {
                    Text(
                        cargoStatusInfo.phrase, style = Pal24MedW
                    )
                    cargoStatusInfo.icon?.let {
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            painterResource(id = it),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
                HorizontalDivider(color = BlueBar, thickness = 1.dp)

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundLightBlue)
                ) {
                    item {
                        DetailedCargoRow(label = "Creation date", value = cargo.creationDate)
                        HorizontalDivider(color = BlueBar, thickness = 1.dp)
                        DetailedCargoRow(label = "Car number", value = cargo.carId.toString())
                        HorizontalDivider(color = BlueBar, thickness = 1.dp)
                        DetailedCargoRow(label = "Description", value = cargo.description)
                        HorizontalDivider(color = BlueBar, thickness = 1.dp)
                    }

                    //TODO
//                    items(points) { point ->
//                        DetailedPointRow(name = point.label, location = point.location)
//                        Spacer(modifier = Modifier.height(8.dp))
//                    }
                }
            }
        }
    }
}