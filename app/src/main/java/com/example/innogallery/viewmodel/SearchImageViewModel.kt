package com.example.innogallery.viewmodel
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.innogallery.model.ApiResponse
//import com.example.innogallery.api.RetrofitInstance
//import io.reactivex.Observer
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.Disposable
//import io.reactivex.schedulers.Schedulers
//
//class SearchImageViewModel : ViewModel() {
//
//    var imageList: MutableLiveData<ApiResponse> = MutableLiveData()
//
//    fun getImageListObserver(): MutableLiveData<ApiResponse> {
//        return imageList
//    }
//
//    fun makeApiCall(query: String) {
//
//        RetrofitInstance.api.searchPhotos(text = query)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(getImageListObserverRx())
//
//    }
//
//    private fun getImageListObserverRx(): Observer<ApiResponse> {
//        return object : Observer<ApiResponse> {
//            override fun onComplete() {
//                //hide progress indicator
//            }
//
//            override fun onNext(t: ApiResponse) {
//                imageList.postValue(t)
//            }
//
//            override fun onError(e: Throwable) {
//                imageList.postValue(null)
//            }
//            override fun onSubscribe(d: Disposable) {
//                //start progress bar
//            }
//        }
//    }
//
//
//}