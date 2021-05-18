package com.example.innogallery.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.innogallery.model.ApiResponse
import com.example.innogallery.repository.GalleryRepository
import com.example.innogallery.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class GalleryViewModelKT(private val galleryRepository: GalleryRepository): ViewModel() {

    val latestImages: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()

    fun getImages() {
        viewModelScope.launch {
            try {
                latestImages.postValue(Resource.Loading())
                val response = galleryRepository.getImages()
                latestImages.postValue(handleBreakingResponse(response))
            } catch (e: Exception) {
                Log.d("Gallery", "getImages: ${e.message}")
            }
        }
    }



    private fun handleBreakingResponse(response: Response<ApiResponse>): Resource<ApiResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }

}