package com.example.logify.enums

import com.example.logify.R
import com.example.logify.ui.theme.GreenStatus
import com.example.logify.ui.theme.LightBlueStatus
import com.example.logify.ui.theme.LightGreenStatus
import com.example.logify.ui.theme.OrangeStatus
import com.example.logify.ui.theme.RedStatus

enum class CargoStatusInfo(
    val color: androidx.compose.ui.graphics.Color,
    val phrase: String,
    val icon: Int? = null,
    val onClick: () -> Unit
) {
    Created(
        color = LightBlueStatus,
        phrase = "Start cargo",
        onClick = { /* Handle Start cargo action */ }
    ),
    Started(
        color = GreenStatus,
        phrase = "Send documents",
        icon = R.drawable.attach,
        onClick = { /* Handle Send documents action */ }
    ),
    InCheck(
        color = OrangeStatus,
        phrase = "On verification",
        icon = R.drawable.folder,
        onClick = { /* Handle On verification action */ }
    ),
    Finished(
        color = LightGreenStatus,
        phrase = "Finished",
        icon = R.drawable.folder,
        onClick = { /* Handle Finished action */ }
    ),
    Problem(
        color = RedStatus,
        phrase = "Problem",
        icon = R.drawable.folder,
        onClick = { /* Handle Problem action */ }
    ),
    Unknown(
        color = androidx.compose.ui.graphics.Color.Transparent,
        phrase = "Unknown",
        onClick = { /* Handle Unknown action */ }
    );

    companion object {
        fun fromStatus(status: String): CargoStatusInfo {
            return when (status) {
                "Created" -> Created
                "Started" -> Started
                "In-check" -> InCheck
                "Finished" -> Finished
                "Problem" -> Problem
                else -> Unknown
            }
        }
    }
}