package com.example.logify.view.screens.employer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logify.R
import com.example.logify.services.FileUtils.openDocument
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.GreenStatus
import com.example.logify.ui.theme.Pal24MedW
import com.example.logify.ui.theme.Pal24SemiW
import com.example.logify.view.components.DocumentRow
import com.example.logify.view.components.EmployerBottomAppBar
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentScreen() {

    var documentList by remember { mutableStateOf(listOf<File>()) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth().offset((-24).dp), contentAlignment = Alignment.Center) {
                        Text(text = "Attached files", style = Pal24SemiW)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO Handle navigation back */ }) {
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
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(modifier = Modifier.weight(0.7f)) {
                items(documentList) { document ->
                    DocumentRow(
                        document = document,
                        onRemove = { file ->
                            documentList = documentList.filterNot { it == file }
                        },
                        onOpen = { file ->
                            openDocument(context, file)
                        }
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.12f),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { /* Handle Approve action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = GreenStatus),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clip(RoundedCornerShape(20))
                        .background(GreenStatus),
                ) {
                    Text(text = "Approve", style = Pal24MedW)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDocumentScreen() {
    DocumentScreen()
}