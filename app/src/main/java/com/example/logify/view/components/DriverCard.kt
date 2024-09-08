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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logify.R
import com.example.logify.data.Cargo
import com.example.logify.data.User
import com.example.logify.enums.CargoStatusInfo
import com.example.logify.ui.theme.BorderStatus
import com.example.logify.ui.theme.CargoCardBackground
import com.example.logify.ui.theme.GreenStatus


@Composable
fun DriverItemCardWithStatus(user: User) {
//    val driverStatusInfo
//TODO call a fun from back-end to check if driver has active cargos now

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
            painter = painterResource(id = R.drawable.driver),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = user.name + " " + user.surname,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.opensans_semibold)),
                    fontSize = 18.sp
                )
            )
        }
        Box(
            modifier = Modifier
                .background(GreenStatus, shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Ready", color = Color.White,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.palanquin_semibold))),
                fontSize = 14.sp)
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
fun DriverItemCard(user: User) {
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
            painter = painterResource(id = R.drawable.driver),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = user.name + " " + user.surname,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.opensans_semibold)),
                    fontSize = 18.sp
                )
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Icon in white circle with black border for "Reject"
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, Color.Black, CircleShape), // Black border around the circle
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = { /* Handle reject click */ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_reject), // Replace with your reject icon resource
                    contentDescription = "Reject",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Icon in white circle with black border for "Chat"
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, Color.Black, CircleShape), // Black border around the circle
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = { /* Handle chat click */ }) {
                Image(
                    painter = painterResource(id = R.drawable.chat), // Replace with your chat icon resource
                    contentDescription = "Chat",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun DriverList(driverItems: List<User>, withStatus: Boolean) {
    if (withStatus){
        LazyColumn {
            items(driverItems) { driver ->
                DriverItemCardWithStatus(user = driver)
            }
        }
    }else {
        LazyColumn {
            items(driverItems) { driver ->
                DriverItemCard(user = driver)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DriverListPreview() {
    val driverItems = listOf(
        User(1, "Name", "Surname", "+484832843824", "Driver"),
        User(1, "Name", "Surname", "+484832843824", "Driver"),
        User(1, "Name", "Surname", "+484832843824", "Driver"),
        User(1, "Name", "Surname", "+484832843824", "Driver")
    )
    DriverList(driverItems = driverItems, false)
}