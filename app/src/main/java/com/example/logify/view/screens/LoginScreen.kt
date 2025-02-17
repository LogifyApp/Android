package com.example.logify.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logify.R
import com.example.logify.ui.theme.Pal20Med
import com.example.logify.ui.theme.PalDark48RegB
import com.example.logify.view.components.CustomPasswordTextField
import com.example.logify.view.components.CustomTextField
import com.example.logify.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: UserViewModel = viewModel()) {
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val (focusRequesterPhone, focusRequesterPassword) = FocusRequester.createRefs()
    val focusManager = LocalFocusManager.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->

        Image(
            painter = painterResource(id = R.drawable.complex_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
            Text(text = "Logify", style = PalDark48RegB)
            Spacer(modifier = Modifier.height(32.dp))
            CustomTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = "Phone Number",
                focusRequester = focusRequesterPhone,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { focusRequesterPassword.requestFocus() })
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomPasswordTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                focusRequester = focusRequesterPassword,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    try {
                        val userResponse = viewModel.login(phoneNumber, password)
                        if (userResponse != null) {
                            viewModel.insertUser(userResponse)
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar("Something went wrong")
                            }
                        }
                    } catch (e: Exception) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Error: ${e.message}")
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp), contentPadding = PaddingValues(vertical = 0.dp)
            ) {
                Text(text = "Log in", style = Pal20Med)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController(), viewModel = viewModel())
}