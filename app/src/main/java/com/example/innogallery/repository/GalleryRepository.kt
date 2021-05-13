package com.example.innogallery.repository

import com.example.innogallery.ApiResponse
import com.example.innogallery.api.RetrofitInstance
import retrofit2.Response

class GalleryRepository {

    suspend fun getImages(): Response<ApiResponse> = RetrofitInstance.api.getLatestPhotos()

}