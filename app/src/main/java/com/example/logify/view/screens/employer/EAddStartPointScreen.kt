package com.example.logify.view.screens.employer

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.BlueBar
import com.example.logify.view.components.EmployerBottomAppBar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EAddStartPointScreen() {

    //TODO Ensure that the required APIs (such as "Places API" and "Maps SDK for Android") are enabled for your project.
    //TODO Update marker position when the camera stops moving + Reverse geocode to update searchQuery if necessary


    val context = LocalContext.current
    val placesClient = remember { Places.createClient(context) }

    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
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
                            Text(text = "Add new cargo", color = Color.White)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLightBlue)
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Choose your start point",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { newValue ->
                    searchQuery = newValue

                    // Perform search when the query is updated
                    performSearch(context, newValue.text, placesClient) { latLng ->
                        markerPosition = latLng
                        cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
                    }
                },
                label = { Text("Write your start point") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueBar,
                    unfocusedBorderColor = BlueBar,
                    containerColor = Color.White
                )
            )

            // Google Map with Marker
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(bottom = 16.dp)
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    onMapLoaded = {
                        cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(markerPosition, 12f))
                    }
                ) {
                    Marker(
                        state = MarkerState(position = markerPosition),
                        title = "Selected Location"
                    )
                }
                LaunchedEffect(cameraPositionState.isMoving) {
                    if (!cameraPositionState.isMoving) {
                        // Update marker position when the camera stops moving
                        markerPosition = cameraPositionState.position.target
                        // Reverse geocode to update searchQuery if necessary
                        // searchQuery = TextFieldValue(getAddressFromLatLng(context, markerPosition))
                    }
                }
            }

            Button(
                onClick = {
                    // Handle continue action
                },
                colors = ButtonDefaults.buttonColors(containerColor = BlueBar),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Continue", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

fun performSearch(context: Context, query: String, placesClient: PlacesClient, onResult: (LatLng) -> Unit) {
    val request = FindAutocompletePredictionsRequest.builder()
        .setQuery(query)
        .build()

    placesClient.findAutocompletePredictions(request)
        .addOnSuccessListener { response ->
            if (response.autocompletePredictions.isNotEmpty()) {
                val firstPrediction = response.autocompletePredictions.first()
                getLatLngFromPlaceId(context, placesClient, firstPrediction.placeId, onResult)
            } else {
                Toast.makeText(context, "No results found", Toast.LENGTH_SHORT).show()
            }
        }
        .addOnFailureListener { exception ->
            Toast.makeText(context, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
        }
}

fun getLatLngFromPlaceId(context: Context, placesClient: PlacesClient, placeId: String, onResult: (LatLng) -> Unit) {
    val request = FetchPlaceRequest.builder(placeId, listOf(com.google.android.libraries.places.api.model.Place.Field.LAT_LNG))
        .build()

    placesClient.fetchPlace(request)
        .addOnSuccessListener { response ->
            val latLng = response.place.latLng
            if (latLng != null) {
                onResult(latLng)
            } else {
                Toast.makeText(context, "LatLng not available for this place", Toast.LENGTH_SHORT).show()
            }
        }
        .addOnFailureListener { exception ->
            Toast.makeText(context, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
        }
}