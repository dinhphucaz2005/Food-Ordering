package com.example.foodordering.util

import android.content.Context
import android.database.Cursor
import android.provider.OpenableColumns
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageHelper @Inject constructor(
    private val context: Context
) {

    fun getFileName(uri: android.net.Uri): String? {
        var fileName: String? = null
        val cursor: Cursor? =
            context.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val displayNameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (displayNameIndex != -1) {
                    fileName = it.getString(displayNameIndex)
                }
            }
        }
        return fileName
    }

}