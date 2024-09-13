package com.example.logify.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.logify.R
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.Open20Reg
import java.io.File

@Composable
fun DocumentRow(document: File, onRemove: (File) -> Unit, onOpen: (File) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(1.dp, BlueBar, shape = RoundedCornerShape(8.dp))
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClickableText(
            text = AnnotatedString(document.name),
            onClick = { onOpen(document) },
            modifier = Modifier.weight(1f),
            style = Open20Reg
        )

        IconButton(
            onClick = { onRemove(document) },
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_reject),
                contentDescription = "Delete Icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}