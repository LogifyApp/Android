package com.example.logify.view.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
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
import com.example.logify.data.CargoStatusInfo
import com.example.logify.data.Point
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.DarkBlue
import com.example.logify.ui.theme.GreenStatus
import com.example.logify.ui.theme.LightBlueStatus
import com.example.logify.ui.theme.LightGreenStatus
import com.example.logify.ui.theme.OrangeStatus
import com.example.logify.ui.theme.RedStatus
import com.example.logify.view.components.DetailedCargoRow
import com.example.logify.view.components.DetailedPointRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedCargoScreen(cargo: Cargo, points: List<Point>) {
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
                            "№${cargo.id}",
                            fontSize = 26.sp,
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.opensans_semibold)),
                                color = Color.White
                            )
                        )
                    }
                },
                actions = {
                    Text(
                        text = cargo.status,
                        color = Color.White,
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.palanquin_semibold)), fontSize = 14.sp),
                        modifier = Modifier
                            .background(
                                getStatusInfo(cargo).color,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BlueBar
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = BlueBar,
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
            ) {
                Spacer(modifier = Modifier.weight(0.5f))
                NavigationBarItem(
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.user),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    selected = true,
                    onClick = {},
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = DarkBlue,
                        unselectedIconColor = BlueBar,
                        indicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(50))
                        .scale(1.5f)
                )
                Spacer(modifier = Modifier.weight(1f))
                NavigationBarItem(
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.cargo),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    selected = false,
                    onClick = {},
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = DarkBlue,
                        unselectedIconColor = BlueBar,
                        indicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(50))
                        .scale(1.5f)
                )
                Spacer(modifier = Modifier.weight(1f))
                NavigationBarItem(
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.chat),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    selected = false,
                    onClick = {},
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = DarkBlue,
                        unselectedIconColor = BlueBar,
                        indicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(50))
                        .scale(1.5f)
                )
                Spacer(modifier = Modifier.weight(0.5f))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLightBlue)
                .padding(innerPadding)
        ) {
            Button(
                onClick = { /* Handle send documents */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(getStatusInfo(cargo).color, shape = RoundedCornerShape(4.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = getStatusInfo(cargo).color)
            ) {
                Text(
                    getStatusInfo(cargo).phrase,
                    color = Color.White,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.palanquin_medium)),
                        fontSize = 24.sp
                    )
                )
                getStatusInfo(cargo).icon?.let {
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
                    DetailedCargoRow(label = "Description", value = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur a ipsum fermentum, consectetur ex quis, tempor est.")
                    HorizontalDivider(color = BlueBar, thickness = 1.dp)
                }

                items(points) { point ->
                    DetailedPointRow(name = point.label, location = point.location)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

fun getStatusInfo(cargo: Cargo): CargoStatusInfo {
    return when (cargo.status) {
        "Created" -> CargoStatusInfo(
            color = LightBlueStatus,
            phrase = "Start cargo",
            onClick = { /* Handle Start cargo action */ }
        )
        "Started" -> CargoStatusInfo(
            color = GreenStatus,
            phrase = "Send documents",
            icon = R.drawable.attach,
            onClick = { /* Handle Send documents action */ }
        )
        "In-check" -> CargoStatusInfo(
            color = OrangeStatus,
            phrase = "On verification",
            icon = R.drawable.folder,
            onClick = { /* Handle On verification action */ }
        )
        "Finished" -> CargoStatusInfo(
            color = LightGreenStatus,
            phrase = "Finished",
            icon = R.drawable.folder,
            onClick = { /* Handle Finished action */ }
        )
        "Problem" -> CargoStatusInfo(
            color = RedStatus,
            phrase = "Problem",
            icon = R.drawable.folder,
            onClick = { /* Handle Problem action */ }
        )
        else -> CargoStatusInfo(
            color = Color.Transparent,
            phrase = "Unknown",
            onClick = { /* Handle Unknown action */ }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DetailedCargoScreenPreview() {
    val cargo = Cargo(id = 31023, status = "Started", creationDate = "4.06.2024", carId = 1, driverId = 1)
    val points = listOf(
        Point(1, "First", "52.3654169959937, 4.933788949153057"),
        Point(2, "Second", "Ijplein en Vogelbuurt")
    )
    DetailedCargoScreen(cargo = cargo, points = points)
}