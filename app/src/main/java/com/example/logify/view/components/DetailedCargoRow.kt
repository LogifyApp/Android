package com.example.logify.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logify.R

@Composable
fun DetailedCargoRow(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = TextStyle(fontFamily = FontFamily(Font(R.font.palanquin_bold)), fontSize = 26.sp))
        Text(text = value, style = TextStyle(fontFamily = FontFamily(Font(R.font.opensans_semibold)), fontSize = 20.sp))
    }
}