package com.example.innogallery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.innogallery.repository.GalleryRepository

class GalleryViewModelFactory(private val galleryRepository: GalleryRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GalleryViewModel(galleryRepository) as T
    }
}