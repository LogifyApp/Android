package com.example.logify.view.screens.driver

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.logify.R
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.Pal15Med
import com.example.logify.ui.theme.Pal20Bold
import com.example.logify.ui.theme.Pal20Med
import com.example.logify.ui.theme.Pal20MedW
import com.example.logify.ui.theme.Pal32Bold
import com.example.logify.view.components.DriverBottomAppBarWithBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    var hasEmployer by remember { mutableStateOf(false) }
    var employerConfirmed by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            DriverBottomAppBarWithBadge(unreadMessageCount = 1)
        },
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLightBlue)
            .imePadding()
    ) { innerPadding ->
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
                    .offset(y = 45.dp)
                    .clip(CircleShape)
                    .background(White)
                    .zIndex(1f),
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
                    text = "+4845393994", style = Pal20Med
                )

                Spacer(modifier = Modifier.height(24.dp))

                Divider(color = BlueBar, thickness = 1.dp)

                ProfileSection(title = "Employer", content = {
                    when {
                        !hasEmployer && !employerConfirmed -> {
                            // State 1: No employer assigned
                            Text(
                                text = "You don’t have an employer yet.",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        }
                        hasEmployer && !employerConfirmed -> {
                            // State 2: Employer request pending
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                CircularIconWithBackground(R.drawable.warning_sign, "Warning")
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Jeremy Dolman",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.palanquin_semibold)),
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.weight(1f)
                                )
                                CircularIconWithBackground(R.drawable.tick, "Confirm") {
                                    employerConfirmed = true
                                    hasEmployer = false
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                CircularIconWithBackground(R.drawable.ic_reject, "Decline") {
                                    hasEmployer = false
                                }
                            }
                        }
                        employerConfirmed -> {
                            // State 3: Employer confirmed
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                Text(
                                    text = "Jeremy Dolman",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.palanquin_semibold)),
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.weight(1f)
                                )
                                CircularIconWithBackground(R.drawable.chat, "Chat") {
                                    // Handle chat action
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                CircularIconWithBackground(R.drawable.ic_reject, "Unconfirm") {
                                    employerConfirmed = false
                                    hasEmployer = true
                                }
                            }
                        }
                    }
                })

                Spacer(modifier = Modifier.height(16.dp))

                Divider(color = BlueBar, thickness = 1.dp)

                ProfileSection(title = "GPS Options", content = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        GPSOption(painterRes = R.drawable.ic_blue_location, label = "Phone's GPS")
                        GPSOption(painterRes = R.drawable.wialon, label = "Wialon")
                    }
                })

                Spacer(modifier = Modifier.height(16.dp))

                Divider(color = BlueBar, thickness = 1.dp)

                ProfileSection(title = "Language", content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.english),
                            contentDescription = "UK Flag",
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "English", fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.palanquin_semibold)), fontWeight = FontWeight.SemiBold)
                    }
                })
            }
        }
    }
}

@Composable
fun ProfileSection(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, style = Pal20Bold)
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@Composable
fun CircularIconWithBackground(iconRes: Int, contentDescription: String, onClick: () -> Unit = {}) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(White)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun GPSOption(painterRes: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = painterRes),
                contentDescription = label,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, style = Pal15Med, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProfileScreen()
}