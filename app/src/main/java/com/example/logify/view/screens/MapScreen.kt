package com.example.logify.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logify.R
import com.example.logify.data.User
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.Open26SemiW
import com.example.logify.view.components.DriverBottomAppBarWithBadge
import com.example.logify.view.components.EmployerBottomAppBar
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
fun MapScreen(user: User) {
    Scaffold(
        bottomBar = {
            if (user.role == "Driver") DriverBottomAppBarWithBadge(unreadMessageCount = 1)
            else if (user.role == "Employer") EmployerBottomAppBar(unreadMessageCount = 1)
        },
        modifier = Modifier.fillMaxSize(),
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
                            Text(text = "Route", style = Open26SemiW)
                            Icon(
                                painter = painterResource(id = R.drawable.location),
                                contentDescription = "Location Icon",
                                tint = Color.White,
                                modifier = Modifier.offset(x = 4.dp).size(24.dp)
                            )
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
    //TODO modify the MapScreen to support multiple cargos by passing a list of Cargo and Point objects.
//fun MapScreen(cargos: List<Cargo>, points: Map<Int, List<Point>>, modifier: Modifier = Modifier) {
//    val initialPosition = LatLng(points.values.first().first().latitude, points.values.first().first().longitude)
//    val cameraPositionState = rememberCameraPositionState {
//        position = CameraPosition.fromLatLngZoom(initialPosition, 12f)
//    }
//
//    GoogleMap(
//        modifier = modifier.fillMaxSize(),
//        cameraPositionState = cameraPositionState
//    ) {
//        cargos.forEach { cargo ->
//            val cargoPoints = points[cargo.id] ?: emptyList()
//
//            // Plot the points for the cargo
//            cargoPoints.forEach { point ->
//                Marker(
//                    state = MarkerState(position = LatLng(point.latitude, point.longitude)),
//                    title = point.label
//                )
//            }
//
//            // Draw polyline for cargo route
//            Polyline(
//                points = cargoPoints.map { LatLng(it.latitude, it.longitude) },
//                color = Color.Blue,
//                width = 5f
//            )
//        }
//    }
}

@Preview(showBackground = true)
@Composable
fun RouteScreenContentPreview() {
    MapScreen(User(1, "Name", "Surname", "+484832843824", "Driver"))
}