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

class SearchImageViewModelKT(val galleryRepository: GalleryRepository): ViewModel() {

    val searchLatestImages: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()

    fun searchImages(text: String) {
        viewModelScope.launch {
            try {
                searchLatestImages.postValue(Resource.Loading())
                val response = galleryRepository.searchImages(text)
                searchLatestImages.postValue(handleSearchBreakingResponse(response))
            } catch (e: Exception) {
                Log.d("Gallery", "getImages: ${e.message}")
            }
        }
    }

    private fun handleSearchBreakingResponse(response: Response<ApiResponse>): Resource<ApiResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }

}