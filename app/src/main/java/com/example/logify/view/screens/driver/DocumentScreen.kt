package com.example.logify.view.screens.driver

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import com.example.logify.services.FileUtils.uriToFile
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.GreenStatus
import com.example.logify.ui.theme.Open18Semi
import com.example.logify.ui.theme.Open26SemiW
import com.example.logify.view.components.DocumentRow
import com.example.logify.view.components.DriverBottomAppBarWithBadge
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentScreen() {

    //TODO implement documentViewModel here where necessary

    var documentList by remember { mutableStateOf(listOf<File>()) }
    val context = LocalContext.current

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments()
    ) { uris ->
        val newFiles = uris.mapNotNull { uri ->
            uriToFile(context, uri)
        }
        documentList = documentList + newFiles
    }

    Scaffold(
        bottomBar = {
            DriverBottomAppBarWithBadge(unreadMessageCount = 1)
        },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth().offset((-24).dp), contentAlignment = Alignment.Center) {
                        Text(text = "Attached files", style = Open26SemiW)
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    filePickerLauncher.launch(arrayOf("application/pdf"))
                },
                colors = ButtonDefaults.buttonColors(containerColor = GreenStatus, contentColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(50))
            ) {
                Text(text = "Add documents", style = Open18Semi)
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.attach),
                    contentDescription = "Attach Icon",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDocumentScreen() {
    DocumentScreen()
}