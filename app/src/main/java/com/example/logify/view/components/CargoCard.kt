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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logify.R
import com.example.logify.data.Cargo
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.BorderStatus
import com.example.logify.ui.theme.CargoCardBackground
import com.example.logify.ui.theme.GreenStatus
import com.example.logify.ui.theme.LightBlueStatus
import com.example.logify.ui.theme.LightGreenStatus
import com.example.logify.ui.theme.OrangeStatus
import com.example.logify.ui.theme.RedStatus
import java.util.Date

@Composable
fun CargoItemCard(cargoItem: Cargo) {
    val statusColor = when (cargoItem.status) {
        "Created" -> LightBlueStatus
        "Started" -> GreenStatus
        "In-check" -> OrangeStatus
        "Finished" -> LightGreenStatus
        "Problem" -> RedStatus
        else -> Color.Transparent
    }

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
                text = "№${cargoItem.id}",
                style = TextStyle(fontFamily = FontFamily(Font(R.font.opensans_semibold)),
                fontSize = 18.sp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .background(statusColor, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = cargoItem.status, color = Color.White,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.palanquin_semibold))),
                    fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = cargoItem.creationDate.toString(),
                style = TextStyle(fontFamily = FontFamily(Font(R.font.opensans_semibold))),
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
fun CargoList(cargoItems: List<Cargo>) {
    LazyColumn {
        items(cargoItems) { cargoItem ->
            CargoItemCard(cargoItem = cargoItem)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CargoListPreview() {
//    val cargoItems = listOf(
//        Cargo(id = 31023, status = "Created", creationDate = "4.05.2024", carId = 1, driverId = 1),
//        Cargo(id = 31024, status = "Started", creationDate = "4.05.2024", carId = 1, driverId = 1),
//        Cargo(id = 31023,  status = "In-check", creationDate = "4.05.2024", carId = 1, driverId = 1),
//        Cargo(id = 31023,  status = "Finished", creationDate = "4.05.2024", carId = 1, driverId = 1),
//        Cargo(id = 31023,  status = "Problem", creationDate = "4.05.2024", carId = 1, driverId = 1)
//    )
//    CargoList(cargoItems = cargoItems)
//}