package com.example.innogallery.api

import com.example.innogallery.ApiResponse
import com.example.innogallery.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryAPI {

    @GET("services/rest/?method=flickr.photos.getRecent")
    suspend fun getLatestPhotos(
        @Query("per_page")
        perPage: Int = 20,
        @Query("page")
        page: Int = 1,
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("format")
        format: String = "json",
        @Query("nojsoncallback")
        jsonCallback: Int = 1,
        @Query("extras")
        extras: String = "url_s"
    ): Response<ApiResponse>

}
