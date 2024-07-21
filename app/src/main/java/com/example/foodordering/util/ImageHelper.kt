package com.example.foodordering.util

import android.database.Cursor
import android.provider.OpenableColumns
import com.example.foodordering.di.AppModule

object ImageHelper {

    fun getFileName(uri: android.net.Uri): String? {
        var fileName: String? = null
        val cursor: Cursor? = AppModule.provideContext().contentResolver.query(uri, null, null, null, null)
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