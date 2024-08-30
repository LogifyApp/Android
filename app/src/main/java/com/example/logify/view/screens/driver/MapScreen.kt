package com.example.logify.view.screens.driver

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.logify.ui.theme.BlueBar
import com.example.logify.view.components.DriverBottomAppBarWithBadge
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen() {
    Scaffold(
        bottomBar = {
            DriverBottomAppBarWithBadge(unreadMessageCount = 1)
        },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Route", color = Color.White)
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
        }
    ) { innerPadding ->
        RouteMap(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun RouteMap(modifier: Modifier = Modifier) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(52.370216, 4.895168), 12f)
    }

    val routePoints = listOf(
        LatLng(52.370216, 4.895168),
        LatLng(52.3676, 4.9041),
        LatLng(52.3645, 4.9141)
    )

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = routePoints.first()),
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
            title = "Start Location"
        )

        routePoints.forEach { point ->
            Marker(
                state = MarkerState(position = point),
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
            )
        }
        Polyline(
            points = routePoints,
            color = Color.Blue,
            width = 5f
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RouteScreenContentPreview() {
    MapScreen()
    //TODO Fix problem with preview (Manifest <meta-data
    //            android:name="com.google.android.gms.version"
    //            android:value="@integer/google_play_services_version" />)
}