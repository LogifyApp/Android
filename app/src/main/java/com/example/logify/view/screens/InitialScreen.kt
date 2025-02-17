package com.example.logify.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logify.R
import com.example.logify.ui.theme.Pal20Med
import com.example.logify.ui.theme.PalDark48RegB

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
            text = "Logify", style = PalDark48RegB
        )
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp), contentPadding = PaddingValues(vertical = 0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(
                text = "Log in", style = Pal20Med
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
                text = "Register", style = Pal20Med
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InitialScreenPreview() {
    InitialScreen(navController = rememberNavController())
}