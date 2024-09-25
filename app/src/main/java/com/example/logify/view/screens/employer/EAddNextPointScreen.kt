package com.example.logify.view.screens.employer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.logify.R
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.Pal18MedW
import com.example.logify.ui.theme.Pal24BoldB
import com.example.logify.ui.theme.Pal24SemiW
import com.example.logify.view.components.EmployerBottomAppBar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EAddNextPointScreen() {

    //TODO Ensure that the required APIs (such as "Places API" and "Maps SDK for Android") are enabled for your project.
    //TODO Update marker position when the camera stops moving + Reverse geocode to update searchQuery if necessary

    val context = LocalContext.current
    val placesClient = remember { Places.createClient(context) }

    var label by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var nextPoint by remember { mutableStateOf(TextFieldValue("")) }
    var isFinalPoint by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(52.370216, 4.895168), 12f)
    }
    var markerPosition by remember { mutableStateOf(LatLng(52.370216, 4.895168)) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.offset((-25).dp)
                        ) {
                            Text(text = "Add new cargo", style = Pal24SemiW)
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle navigation back */ }) {
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
                .verticalScroll(scrollState)
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Choose your next point", style = Pal24BoldB,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            HorizontalDivider(
                color = BlueBar,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = label,
                onValueChange = { newValue -> label = newValue },
                label = { Text("Label of point") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueBar,
                    unfocusedBorderColor = BlueBar,
                    containerColor = Color.White
                )
            )

            HorizontalDivider(color = BlueBar, thickness = 1.dp)

            OutlinedTextField(
                value = description,
                onValueChange = { newValue -> description = newValue },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueBar,
                    unfocusedBorderColor = BlueBar,
                    containerColor = Color.White
                ),
                maxLines = 3
            )

            HorizontalDivider(color = BlueBar, thickness = 1.dp)

            OutlinedTextField(
                value = nextPoint,
                onValueChange = { newValue ->
                    nextPoint = newValue
                    performSearch(context, newValue.text, placesClient) { latLng ->
                        markerPosition = latLng
                        cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
                    }
                },
                label = { Text("Next point") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueBar,
                    unfocusedBorderColor = BlueBar,
                    containerColor = Color.White
                )
            )

            HorizontalDivider(color = BlueBar, thickness = 1.dp)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(16.dp)
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    onMapLoaded = {
                        cameraPositionState.move(
                            CameraUpdateFactory.newLatLngZoom(
                                markerPosition,
                                12f
                            )
                        )
                    }
                ) {
                    Marker(
                        state = MarkerState(position = markerPosition),
                        title = "Selected Location"
                    )
                }
                LaunchedEffect(cameraPositionState.isMoving) {
                    if (!cameraPositionState.isMoving) {
                        markerPosition = cameraPositionState.position.target
                    }
                }
            }

            HorizontalDivider(color = BlueBar, thickness = 1.dp)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Is it final point?", style = Pal24BoldB)
                Spacer(modifier = Modifier.width(8.dp))
                Checkbox(
                    checked = isFinalPoint,
                    onCheckedChange = { isFinalPoint = it }
                )
            }

            Button(
                onClick = { /* Handle continue action */ },
                colors = ButtonDefaults.buttonColors(containerColor = BlueBar),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Continue", style = Pal18MedW)
            }
        }
    }
}