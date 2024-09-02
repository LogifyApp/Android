package com.example.logify.services

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File

object FileUtils {

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
}