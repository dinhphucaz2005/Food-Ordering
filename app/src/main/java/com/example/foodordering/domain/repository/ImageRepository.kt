package com.example.foodordering.domain.repository

import android.net.Uri

interface ImageRepository {
    suspend fun getImages(): List<Uri>
}
