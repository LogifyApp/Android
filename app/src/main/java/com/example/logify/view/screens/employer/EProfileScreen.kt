package com.example.logify.view.screens.employer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.logify.R
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.Pal20Med
import com.example.logify.ui.theme.Pal20MedW
import com.example.logify.ui.theme.Pal24BoldB
import com.example.logify.ui.theme.Pal32Bold
import com.example.logify.view.components.EmployerBottomAppBar
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EProfileScreen() {

    val amsterdam = LatLng(52.3676, 4.9041)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(amsterdam, 10f)
    }

    Scaffold(
        bottomBar = {
            EmployerBottomAppBar(unreadMessageCount = 1)
        },
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLightBlue)
            .imePadding()
    )  { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().background(color = BackgroundLightBlue)) {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BlueBar
                ),
                title = {
                    Text(
                        text = "Profile", style = Pal20MedW,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp)
                            .offset(y = (-5).dp),
                        textAlign = TextAlign.Center
                    )
                },
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .zIndex(1f)
            )
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 70.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .zIndex(1f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(100.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundLightBlue)
                    .padding(innerPadding)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.complex_background_cropped),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 170.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "John Ridkey", style = Pal32Bold
                    )
                    Text(
                        text = "+4845393994", style = Pal20Med
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    HorizontalDivider(color = BlueBar, thickness = 1.dp)

                    Text(text = "My Cars", style = Pal32Bold,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { /*TODO add onClick */ })

                    Spacer(modifier = Modifier.height(8.dp))

                    HorizontalDivider(color = BlueBar, thickness = 1.dp)

                    ProfileSection(title = "My map") {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            GoogleMap(
                                modifier = Modifier.fillMaxSize(),
                                cameraPositionState = cameraPositionState
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDivider(color = BlueBar, thickness = 1.dp)

                    ProfileSection(title = "Language") {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.english),
                                contentDescription = "Language",
                                modifier = Modifier.size(50.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "English",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileSection(title: String, content: @Composable () -> Unit?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = Pal24BoldB,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmployerProfileScreen() {
    EProfileScreen()
}