package com.example.logify.view.screens.employer

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logify.R
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.Pal24SemiW
import com.example.logify.view.components.CustomTextField
import com.example.logify.view.components.EmployerBottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EAddNewDriverScreen(navController: NavController) {

    var phoneNumber by remember { mutableStateOf("") }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .offset((-24).dp), contentAlignment = Alignment.Center) {
                        Text(text = "Add new driver", style = Pal24SemiW)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO Handle navigation back */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BlueBar
                )
            )
        },
        bottomBar = {
            EmployerBottomAppBar(unreadMessageCount = 1)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
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
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(45.dp))

                Box(modifier = Modifier.padding(horizontal = 25.dp)){
                    CustomTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = "Enter phone number",
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                keyboardController?.hide()
                                if (phoneNumberExists(phoneNumber)) {
                                    navController.navigate("driver_details/$phoneNumber")
                                } else {
                                    Toast.makeText(context, "Phone number not found", Toast.LENGTH_SHORT).show()
                                }
                            }
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Search
                        )
                    )
                }

                Spacer(modifier = Modifier.height(60.dp))

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .aspectRatio(1f)
                )
            }
        }
    }
}

fun phoneNumberExists(phoneNumber: String): Boolean {
    // Here, you would check if the phone number exists in your database
    //TODO refactor and add to ViewModel structure
    return phoneNumber == "+4845393994"
}

@Preview(showBackground = true)
@Composable
fun PreviewEAddNewDriverScreen() {
    val navController = rememberNavController()
    EAddNewDriverScreen(navController)
}