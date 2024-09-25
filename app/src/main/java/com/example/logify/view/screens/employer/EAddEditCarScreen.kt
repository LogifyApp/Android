package com.example.logify.view.screens.employer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logify.R
import com.example.logify.data.Car
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.GreenStatus
import com.example.logify.ui.theme.Pal24SemiW
import com.example.logify.ui.theme.Pal26Bold
import com.example.logify.view.components.CustomTextField
import com.example.logify.view.components.EmployerBottomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EAddEditCarScreen(
    car: Car? = null,
    currentUserId: Int, // TODO will be changed for UserViewModel
    onSave: (Car) -> Unit
) {

    //TODO fix error view when field is empty (looks strange right now)

    var carNumber by remember { mutableStateOf(car?.number ?: "") }
    var carBrand by remember { mutableStateOf(car?.brand ?: "") }
    var carModel by remember { mutableStateOf(car?.model ?: "") }

    var showError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (car == null) "Add new car" else "Edit car",
                            style = Pal24SemiW
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BlueBar)
            )
        },
        bottomBar = { EmployerBottomAppBar(unreadMessageCount = 1) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLightBlue)
        ) {
            Image(
                painter = painterResource(id = R.drawable.complex_background_cropped),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier.fillMaxWidth().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Car number", style = Pal26Bold)
                Spacer(modifier = Modifier.height(14.dp))

                Box(modifier = Modifier.padding(horizontal = 24.dp)) {
                    CustomTextField(
                        value = carNumber,
                        onValueChange = { carNumber = it; showError = false },
                        label = "Car number",
                        isError = showError && carNumber.isEmpty()
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(color = BlueBar, thickness = 1.dp)
                Text(text = "Brand", style = Pal26Bold)
                Spacer(modifier = Modifier.height(14.dp))

                Box(modifier = Modifier.padding(horizontal = 24.dp)) {
                    CustomTextField(
                        value = carBrand,
                        onValueChange = { carBrand = it; showError = false },
                        label = "Brand",
                        isError = showError && carBrand.isEmpty()
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(color = BlueBar, thickness = 1.dp)
                Text(text = "Model", style = Pal26Bold)
                Spacer(modifier = Modifier.height(14.dp))

                Box(modifier = Modifier.padding(horizontal = 24.dp)) {
                    CustomTextField(
                        value = carModel,
                        onValueChange = { carModel = it; showError = false },
                        label = "Model",
                        isError = showError && carModel.isEmpty()
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        if (carNumber.isEmpty() || carBrand.isEmpty() || carModel.isEmpty()) {
                            showError = true
                        } else {
                            onSave(
                                Car(
                                    number = carNumber,
                                    model = carModel,
                                    brand = carBrand,
                                    status = car?.status ?: true,
                                    employerId = car?.employerId ?: currentUserId
                                )
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenStatus)
                ) {
                    Text(
                        text = if (car == null) "Add a car" else "Edit car",
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewAddCar() {
    EAddEditCarScreen(currentUserId = 1 ,onSave = {})
}

@Preview
@Composable
fun PreviewEditCar() {
    EAddEditCarScreen(
        car = Car(number = "XYZ987", model = "Civic", brand = "Honda", true, 1),
        currentUserId = 1,
        onSave = {}
    )
}