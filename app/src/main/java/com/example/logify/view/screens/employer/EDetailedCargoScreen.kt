package com.example.logify.view.screens.employer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logify.R
import com.example.logify.data.Cargo
import com.example.logify.enums.CargoStatusInfo
import com.example.logify.ui.theme.*
import com.example.logify.view.components.DetailedCargoRow
import com.example.logify.view.components.EmployerBottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun EDetailedCargoScreen(cargoId: Int, cargoViewModel: CargoViewModel = viewModel(), chatId: Int) {
fun EDetailedCargoScreen(cargoId: Int, chatId: Int) {
    var cargo by remember {
        mutableStateOf( Cargo(131231, "Created", "01.08.1980", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Curabitur a ipsum fermentum, consectetur ex quis, tempor est. elementum mi ligula eu est. " +
            "Duis hendrerit ullamcorper justo", 1, 1, 1)
        )
    }


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
                        Text(
                            "№${cargo?.id}", style = Open26SemiW
                        )
                    }
                },
                navigationIcon = {
                    Row {
                        Spacer(modifier = Modifier.width(8.dp)) // Move icon slightly to the right
                        IconButton(onClick = { cargo = cargo.copy(status = "Problem") }) {
                            Icon(
                                painterResource(id = R.drawable.problem_report), // Replace with your report icon
                                contentDescription = "Report",
                                tint = OrangeStatus,
                                modifier = Modifier.size(28.dp)
                            )
                        }
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
            EmployerBottomAppBar(unreadMessageCount = 1)
        }
    ) { innerPadding ->
        Image(
            painter = painterResource(id = R.drawable.complex_background_cropped),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            cargo?.let { cargo ->
                Button(
                    onClick = { /* Handle send documents */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(20.dp)
                        .clip(RoundedCornerShape(20))
                        .background(AttachedStatus),
                    colors = ButtonDefaults.buttonColors(containerColor = AttachedStatus)
                ) {
                    Text(
                        "Attached documents", style = Pal24MedW,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painterResource(id = R.drawable.attach),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
                HorizontalDivider(color = BlueBar, thickness = 1.dp)


                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    item {
                        DetailedCargoRow(label = "Creation date", value = cargo.creationDate)
                        HorizontalDivider(color = BlueBar, thickness = 1.dp)
                        DetailedCargoRow(label = "Car number", value = cargo.carId.toString())
                        HorizontalDivider(color = BlueBar, thickness = 1.dp)
                        DetailedCargoRow(
                            label = "Description",
                            value = cargo.description,
                            trailingIcon = {
                                IconButton(onClick = { /* Handle edit description */ }) {
                                    Icon(
                                        painterResource(id = R.drawable.edit),
                                        contentDescription = "Edit description",
                                        tint = Color.Black,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        )
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