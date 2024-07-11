package com.example.logify.data

import androidx.compose.ui.graphics.Color

data class CargoStatusInfo(
    val color: Color,
    val phrase: String,
    val icon: Int? = null,
    val onClick: () -> Unit
)