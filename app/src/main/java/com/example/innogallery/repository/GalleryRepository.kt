package com.example.innogallery.repository

import com.example.innogallery.model.ApiResponse
import com.example.innogallery.api.RetrofitInstance
import retrofit2.Response


class GalleryRepository {

    suspend fun getImages(): Response<ApiResponse> = RetrofitInstance.api.getLatestImages()

    suspend fun searchImages(text: String): Response<ApiResponse> = RetrofitInstance.api.searchImages(text = text)

}



