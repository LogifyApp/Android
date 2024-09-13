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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logify.R
import com.example.logify.data.User
import com.example.logify.ui.theme.BorderStatus
import com.example.logify.ui.theme.CargoCardBackground
import com.example.logify.ui.theme.GreenStatus
import com.example.logify.ui.theme.Open18Semi
import com.example.logify.ui.theme.Pal14SemiW
import com.example.logify.ui.theme.Pal20Med


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
                text = user.name + " " + user.surname, style = Open18Semi
            )
        }
        Box(
            modifier = Modifier
                .background(GreenStatus, shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Ready", style = Pal14SemiW
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
                text = user.name + " " + user.surname, style = Open18Semi
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
                    painter = painterResource(id = R.drawable.chat),
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
    if (driverItems.isEmpty()){
        Text(text = "You don’t have any employees yet", style = Pal20Med, textAlign = TextAlign.Center)
    }else {
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