package com.example.logify.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logify.R

@Composable
fun InitialScreen(navController: NavController) {
    Image(
        painter = painterResource(id = R.drawable.complex_background),
        contentDescription = null, contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logify Logo",
            modifier = Modifier.size(220.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Logify", fontSize = 48.sp, color = Color.Black,
            style = TextStyle(
                fontFamily = FontFamily(
                    Font(
                        R.font.palanquindark_regular,
                        FontWeight.Normal
                    )
                )
            )
        )
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp), contentPadding = PaddingValues(vertical = 0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(
                text = "Log in", fontSize = 20.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.palanquin_medium)))
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp), contentPadding = PaddingValues(vertical = 0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(
                text = "Register", fontSize = 20.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.palanquin_medium)))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InitialScreenPreview() {
    InitialScreen(navController = rememberNavController())
}