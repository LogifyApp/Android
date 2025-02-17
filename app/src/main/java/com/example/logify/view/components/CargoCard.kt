package com.example.logify.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logify.R
import com.example.logify.data.Cargo
import com.example.logify.enums.CargoStatusInfo
import com.example.logify.ui.theme.BorderStatus
import com.example.logify.ui.theme.CargoCardBackground
import com.example.logify.ui.theme.Open14Semi
import com.example.logify.ui.theme.Open18Semi
import com.example.logify.ui.theme.Pal14SemiW

@Composable
fun CargoItemCard(cargo: Cargo) {
    val cargoStatusInfo = CargoStatusInfo.fromStatus(cargo.status)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(CargoCardBackground, shape = RoundedCornerShape(16.dp))
            .border(1.dp, BorderStatus, shape = RoundedCornerShape(16.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.delivery_truck),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "№${cargo.id}", style = Open18Semi
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .background(cargoStatusInfo.color, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = cargo.status, style = Pal14SemiW
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = cargo.creationDate, style = Open14Semi
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
fun CargoList(cargoItems: List<Cargo>) {
    LazyColumn {
        items(cargoItems) { cargo ->
            CargoItemCard(cargo = cargo)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CargoListPreview() {
    val cargoItems = listOf(
        Cargo(id = 31023, status = "Created", creationDate = "4.05.2024", carId = 1, driverId = 1, description = "sfdfildnfnsdnfisfnsdlfnsd", employerId = 1),
        Cargo(id = 31024, status = "Started", creationDate = "4.05.2024", carId = 1, driverId = 1, description = "sfdfildnfnsdnfisfnsdlfnsd", employerId = 1),
        Cargo(id = 31023,  status = "In-check", creationDate = "4.05.2024", carId = 1, driverId = 1, description = "sfdfildnfnsdnfisfnsdlfnsd", employerId = 1),
        Cargo(id = 31023,  status = "Finished", creationDate = "4.05.2024", carId = 1, driverId = 1, description = "sfdfildnfnsdnfisfnsdlfnsd", employerId = 1),
        Cargo(id = 31023,  status = "Problem", creationDate = "4.05.2024", carId = 1, driverId = 1, description = "sfdfildnfnsdnfisfnsdlfnsd", employerId = 1)
    )
    CargoList(cargoItems = cargoItems)
}