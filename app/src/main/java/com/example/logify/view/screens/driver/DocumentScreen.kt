package com.example.logify.view.screens.driver

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.logify.R
import com.example.logify.ui.theme.BackgroundLightBlue
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.GreenStatus
import com.example.logify.view.components.DriverBottomAppBarWithBadge
import com.example.logify.viewmodel.DocumentViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentScreen(documentViewModel: DocumentViewModel = hiltViewModel()) {

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
                        Text(text = "Attached files", color = Color.White)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLightBlue)
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
                Text(text = "Add documents", fontSize = 18.sp)
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
            style = MaterialTheme.typography.bodyLarge
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

fun openDocument(context: Context, file: File) {
    try {
        val uri: Uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
        val mimeType = getMimeType(file)

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, mimeType)
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "No application available to open this file", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Toast.makeText(context, "Cannot open ${file.name}", Toast.LENGTH_SHORT).show()
    }
}

fun getMimeType(file: File): String? {
    val extension = MimeTypeMap.getFileExtensionFromUrl(file.toString())
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.lowercase())
}

fun uriToFile(context: Context, uri: Uri): File? {
    val filePathColumn = arrayOf(android.provider.MediaStore.Files.FileColumns.DATA)
    val cursor = context.contentResolver.query(uri, filePathColumn, null, null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            val columnIndex = it.getColumnIndexOrThrow(filePathColumn[0])
            val filePath = it.getString(columnIndex)
            return File(filePath)
        }
    }
    return null
}

@Preview(showBackground = true)
@Composable
fun PreviewDocumentScreen() {
    DocumentScreen()
}