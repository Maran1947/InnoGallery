package com.example.innogallery.api

import com.example.innogallery.model.ApiResponse
import com.example.innogallery.util.Constants.Companion.API_KEY
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryAPI {

    @GET("services/rest/?method=flickr.photos.getRecent")
    fun getLatestPhotos(
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
    ): Single<ApiResponse>

    @GET("services/rest/?method=flickr.photos.search")
    fun searchPhotos(
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
        extras: String = "url_s",
        @Query("text")
        text: String,
    ) : Observable<ApiResponse>

    @GET("services/rest/?method=flickr.photos.getRecent")
    suspend fun getLatestImages(
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

    @GET("services/rest/?method=flickr.photos.search")
    suspend fun searchImages(
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
            extras: String = "url_s",
            @Query("text")
            text: String,
    ) : Response<ApiResponse>
}
