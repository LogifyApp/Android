package com.example.logify.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.logify.ui.theme.GreenStatus

@Composable
fun AddFloatingButton(modifier: Modifier = Modifier, imageVector: ImageVector?, idOfDrawable: Int?) {

    //TODO Add onClick and change parameters on fun based on it

    FloatingActionButton(
        onClick = { /* Handle click */ },
        containerColor = GreenStatus,
        contentColor = Color.White,
        modifier = modifier
            .shadow(5.dp, CircleShape, clip = true)
    ) {
        if (imageVector != null) {
            Icon(
                imageVector = imageVector,
                contentDescription = "",
                modifier = Modifier.padding(12.dp)
            )
        } else if (idOfDrawable != null) {
            Image(
                painter = painterResource(id = idOfDrawable),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}