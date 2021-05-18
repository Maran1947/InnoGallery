package com.example.innogallery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.innogallery.repository.GalleryRepository

class SearchViewModelFactory(private val galleryRepository: GalleryRepository):
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchImageViewModelKT(galleryRepository) as T
    }
}