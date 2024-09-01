package com.example.logify.view.screens.employer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logify.R
import com.example.logify.data.Cargo
import com.example.logify.enums.CargoStatusInfo
import com.example.logify.ui.theme.*
import com.example.logify.view.components.DetailedCargoRow
import com.example.logify.view.components.DriverBottomAppBarWithBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun EDetailedCargoScreen(cargoId: Int, cargoViewModel: CargoViewModel = viewModel(), chatId: Int) {
fun EDetailedCargoScreen(cargoId: Int, chatId: Int) {
    val cargo = Cargo(131231, "Created", "01.08.1980", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Curabitur a ipsum fermentum, consectetur ex quis, tempor est. elementum mi ligula eu est. " +
            "Duis hendrerit ullamcorper justo", 1, 1, 1)


//    val cargo by cargoViewModel.selectedCargo.observeAsState()

    val cargoStatusInfo = CargoStatusInfo.fromStatus(cargo?.status ?: "Problem")

//    //TODO val points by pointViewModel.points.observeAsState(emptyList())
//
//    val unreadMessageCount by cargoViewModel.unreadMessageCount.collectAsState(0)
//
//    LaunchedEffect(Unit) {
//        cargo?.let { cargoViewModel.loadCargosByDriver(employerId = it.employerId, driverId = it.driverId) }
//    }
//
//    LaunchedEffect(Unit) {
//        cargoViewModel.fetchUnreadMessageCount(chatId)
//    }

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
                            "№${cargo?.id}",
                            fontSize = 26.sp,
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.opensans_semibold)),
                                color = Color.White
                            )
                        )
                    }
                },
                actions = {
                    cargo?.let {
                        Text(
                            text = it.status,
                            color = Color.White,
                            style = TextStyle(fontFamily = FontFamily(Font(R.font.palanquin_semibold)), fontSize = 14.sp),
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
            DriverBottomAppBarWithBadge(unreadMessageCount = 1)
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
                        .background(AttachedStatus, shape = RoundedCornerShape(4.dp)), // Always green button
                    colors = ButtonDefaults.buttonColors(containerColor = AttachedStatus)
                ) {
                    Text(
                        "Attached documents",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.palanquin_medium)),
                            fontSize = 24.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painterResource(id = R.drawable.attach), // Attach icon
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
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

@Preview
@Composable
fun Preview(){
    EDetailedCargoScreen(1, 1)
}