package com.example.logify.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logify.R
import com.example.logify.data.Cargo
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.DarkBlue
import com.example.logify.view.components.CargoList
import com.example.logify.view.components.CustomSearchField


@Composable
fun CargoScreen(cargoItems: List<Cargo>) {
    var search by remember { mutableStateOf("") }
    var filteredItems by remember { mutableStateOf(cargoItems) }

    LaunchedEffect(search) {
        filteredItems = if (search.isEmpty()) {
            cargoItems
        } else {
            cargoItems.filter { it.id.toString().contains(search) || it.status.contains(search, ignoreCase = true) }
        }
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BlueBar)
                    .padding(24.dp)
            ) {
                CustomSearchField(
                    value = search,
                    onValueChange = { search = it },
                    placeholder = "Search"
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = BlueBar,
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 4.dp),
            ) {
                Spacer(modifier = Modifier.size(30.dp))
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.user), contentDescription = null, modifier = Modifier.size(40.dp)) },
                    selected = true,
                    onClick = {},
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = DarkBlue,
                        unselectedIconColor = BlueBar,
                        indicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(50))
                        .scale(1.3f)
                )
                Spacer(modifier = Modifier.size(60.dp))
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.cargo), contentDescription = null, modifier = Modifier.size(40.dp)) },
                    selected = false,
                    onClick = {},
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = DarkBlue,
                        unselectedIconColor = BlueBar,
                        indicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(50))
                        .scale(1.3f)
                )
                Spacer(modifier = Modifier.size(60.dp))
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.chat), contentDescription = null, modifier = Modifier.size(40.dp)) },
                    selected = false,
                    onClick = {},
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = DarkBlue,
                        unselectedIconColor = BlueBar,
                        indicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(50))
                        .scale(1.3f)
                )
                Spacer(modifier = Modifier.size(30.dp))

            }
        }
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

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    val cargoItems = listOf(
        Cargo(id = 31023, status = "Created", creationDate = "4.05.2024", carId = 1, driverId = 1),
        Cargo(id = 31024, status = "Started", creationDate = "4.05.2024", carId = 1, driverId = 1),
        Cargo(id = 31023,  status = "In-check", creationDate = "4.05.2024", carId = 1, driverId = 1),
        Cargo(id = 31023,  status = "Finished", creationDate = "4.05.2024", carId = 1, driverId = 1),
        Cargo(id = 31023,  status = "Finished", creationDate = "4.05.2024", carId = 1, driverId = 1),
        Cargo(id = 31023,  status = "Finished", creationDate = "4.05.2024", carId = 1, driverId = 1),
        Cargo(id = 31023,  status = "Finished", creationDate = "4.05.2024", carId = 1, driverId = 1),
        Cargo(id = 31023,  status = "Finished", creationDate = "4.05.2024", carId = 1, driverId = 1),
        Cargo(id = 31023,  status = "Finished", creationDate = "4.05.2024", carId = 1, driverId = 1),
        Cargo(id = 31023,  status = "Finished", creationDate = "4.05.2024", carId = 1, driverId = 1),
        Cargo(id = 31023,  status = "Finished", creationDate = "4.05.2024", carId = 1, driverId = 1),
        Cargo(id = 31023,  status = "Problem", creationDate = "4.05.2024", carId = 1, driverId = 1)
    )
    CargoScreen(cargoItems = cargoItems)
}