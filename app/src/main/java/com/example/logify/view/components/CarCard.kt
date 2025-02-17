package com.example.logify.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logify.R
import com.example.logify.data.Car
import com.example.logify.data.User
import com.example.logify.ui.theme.BorderStatus
import com.example.logify.ui.theme.CargoCardBackground
import com.example.logify.ui.theme.GreenStatus
import com.example.logify.ui.theme.Open18Semi
import com.example.logify.ui.theme.Pal12Reg
import com.example.logify.ui.theme.Pal14SemiW
import com.example.logify.ui.theme.RedStatus


@Composable
fun CarItemCard(car: Car) {
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
                text = car.number, style = Open18Semi
            )
            Text(
                text = car.brand + " " + car.model, style = Pal12Reg
            )
        }
        Box(
            modifier = Modifier
                .background(if(car.status) GreenStatus else RedStatus, shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = if(car.status) "Ready" else "In-ride", style = Pal14SemiW
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
fun AddCarItemCard(car: Car) {
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
                text = car.number, style = Open18Semi
            )
            Text(
                text = car.brand + " " + car.model, style = Pal12Reg
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, Color.Black, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = { /* Handle reject click */ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_reject),
                    contentDescription = "Reject",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, Color.Black, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = { /* Handle chat click */ }) {
                Image(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "Edit",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun AddCarList(carItems: List<Car>) {
    LazyColumn {
        items(carItems) { car ->
            AddCarItemCard(car = car)
        }
    }
}

@Composable
fun CarList(carItems: List<Car>) {
    LazyColumn {
        items(carItems) { car ->
            CarItemCard(car = car)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarListPreview() {
    val carItems = listOf(
        Car("C3JN6C", "Ducatto", "Renault", true, 1),
        Car("C31NAC", "Ducatto", "Renault", true, 1),
        Car("CNA75C", "Ducatto", "Renault", false, 1),
        Car("CN3A6C", "Ducatto", "Renault", false, 1)
    )
    AddCarList(carItems = carItems)
}