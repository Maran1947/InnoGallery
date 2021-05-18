package com.example.innogallery.viewmodel
//
//import androidx.lifecycle.*
//import androidx.paging.PagingData
//import com.example.innogallery.model.Photo
//import io.reactivex.disposables.CompositeDisposable
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//
//
//@ExperimentalCoroutinesApi
//class GalleryViewModel() : ViewModel() {

//    private val compositeDisposable = CompositeDisposable()

//    private val galleryRepository = GalleryRepository(imagePagingSource)
//    private val imagePagingSource = ImagePagingSource()

//    var imageList: MutableLiveData<PagingData<Photo>> = MutableLiveData()

//    init {
//        RetrofitInstance.api.getLatestPhotos()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(getImageListObserverRx())

//        compositeDisposable.add(
//                latestImages()
//                        .cachedIn(viewModelScope)
//                        .subscribe {
//                            imageList.postValue(it)
//                        }
//        )

//    }

//
//    private fun latestImages(): Flowable<PagingData<Photo>> {
//        return Pager(
//                config = PagingConfig(
//                        pageSize = 6
//                ),
//        pagingSourceFactory = { ImagePagingSource() }
//        ).flowable
//    }

//    fun getImageListObserverRx(): Observer<ApiResponse> {
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
//    override fun onCleared() {
//        super.onCleared()
//        compositeDisposable.dispose()
//    }

//}