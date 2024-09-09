package com.example.logify.view.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logify.R
import com.example.logify.dto.UserDto
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.DarkBlue
import com.example.logify.ui.theme.LightBlue
import com.example.logify.ui.theme.Pal18Med
import com.example.logify.ui.theme.Pal20Med
import com.example.logify.ui.theme.Pal24BoldB
import com.example.logify.view.components.CustomPasswordTextField
import com.example.logify.view.components.CustomTextField
import com.example.logify.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(navController: NavController, viewModel: UserViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("Driver") }

    val (focusRequesterName, focusRequesterSurname, focusRequesterPhone, focusRequesterPassword) = FocusRequester.createRefs()
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
            Text(
                text = "Who are you?", textAlign = TextAlign.Center, style = Pal24BoldB
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Transparent),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { role = "Driver" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (role == "Driver") DarkBlue else LightBlue,
                        contentColor = if (role == "Driver") Color.White else Color.Black
                    ),
                    shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
                    modifier = Modifier
                        .weight(1f)
                        .graphicsLayer {
                            alpha = if (role == "Employer") 0.75f else 1f
                        },
                    border = if (role == "Driver") null else BorderStroke(5.dp, BackgroundLightBlue)
                ) {
                    Text(
                        text = "Driver", style = Pal18Med
                    )
                }
                Button(
                    onClick = { role = "Employer" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (role == "Employer") DarkBlue else LightBlue,
                        contentColor = if (role == "Employer") Color.White else Color.Black
                    ),
                    shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
                    modifier = Modifier
                        .weight(1f)
                        .graphicsLayer {
                            alpha = if (role == "Driver") 0.75f else 1f
                        },
                    border = if (role == "Employer") null else BorderStroke(3.dp, BackgroundLightBlue)
                ) {
                    Text(
                        text = "Employer", style = Pal18Med
                    )
                }
            }
            Spacer(modifier = Modifier.height(38.dp))
            CustomTextField(
                value = name,
                onValueChange = { name = it },
                label = "Name",
                focusRequester = focusRequesterName,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { focusRequesterSurname.requestFocus() })
            )
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(
                value = surname,
                onValueChange = { surname = it },
                label = "Surname",
                focusRequester = focusRequesterSurname,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { focusRequesterPhone.requestFocus() })
            )
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = "Phone number",
                focusRequester = focusRequesterPhone,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { focusRequesterPassword.requestFocus() })
            )
            Spacer(modifier = Modifier.height(12.dp))
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
                    val newUser = UserDto(
                        phoneNumber = phoneNumber,
                        name = name,
                        surname = surname,
                        password = password,
                        role = role
                    )
                    try {
                        val userResponse = viewModel.register(newUser)
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
                    .height(48.dp)
            ) {
                Text(
                    text = "Register", style = Pal20Med
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = rememberNavController(), viewModel = viewModel())
}