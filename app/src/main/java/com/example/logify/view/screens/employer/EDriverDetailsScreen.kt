package com.example.logify.view.screens.employer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.logify.R
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.GreenStatus
import com.example.logify.ui.theme.Open18Semi
import com.example.logify.ui.theme.Pal20Med
import com.example.logify.ui.theme.Pal32Bold
import com.example.logify.view.components.DriverBottomAppBarWithBadge
import com.example.logify.view.components.EmployerBottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EDriverDetailsScreen(phoneNumber: String) {

    Scaffold(
        bottomBar = {
            EmployerBottomAppBar(unreadMessageCount = 1)
        },
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLightBlue)
            .imePadding()
    ) { innerPadding ->
        Box(
            modifier = Modifier
            .fillMaxSize()
        ) {
            TopAppBar(
                title = {
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO Handle navigation back */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BlueBar
                ),
                modifier = Modifier.zIndex(1f)
            )
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 30.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .zIndex(2f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(100.dp)
                )
            }

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
                    .padding(top = 190.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "John Ridkey", style = Pal32Bold
                )
                Text(
                    text = phoneNumber, style = Pal20Med
                )

                Spacer(modifier = Modifier.height(35.dp))

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .aspectRatio(1f)
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                              //TODO onClick
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = GreenStatus, contentColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(50))
                ) {
                    Text(text = "Send request", style = Open18Semi)
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDriverDetailsScreen() {
    EDriverDetailsScreen("+4845393994")
}