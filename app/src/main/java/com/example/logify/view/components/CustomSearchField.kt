package com.example.logify.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.logify.ui.theme.Open14Med

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, style = Open14Med) },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                modifier = Modifier.size(24.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(19.dp))
            .background(Color.White)
            .height(48.dp)
            .clipToBounds(),
        textStyle = Open14Med,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
            }
        )
    )
}