package com.example.innogallery.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.innogallery.R
import com.example.innogallery.adapter.GalleryAdapter
import com.example.innogallery.databinding.ActivitySearchImageBinding
import com.example.innogallery.repository.GalleryRepository
import com.example.innogallery.util.Constants.Companion.SEARCH_IMAGE_TIME_DELAY
import com.example.innogallery.util.Resource
import com.example.innogallery.viewmodel.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchImageActivity : AppCompatActivity() {

    private lateinit var recyclerViewSearchImage: RecyclerView
    private  lateinit var galleryAdapter: GalleryAdapter
    private lateinit var sProgressBar: ProgressBar
    private lateinit var tvShow: TextView
    private lateinit var galleryRepository: GalleryRepository
    private lateinit var etSearchQuery: EditText
    private lateinit var searchImageViewModelKT: SearchImageViewModelKT

    private lateinit var binding: ActivitySearchImageBinding

    //    private lateinit var etSearchQuery: SearchView
    //    private lateinit var searchImageViewModel: SearchImageViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etSearchQuery = binding.etSearchImage
        sProgressBar = binding.pbSearch
        tvShow = binding.tvShow

        galleryRepository = GalleryRepository()

        val searchViewModelFactory = SearchViewModelFactory(galleryRepository)
        searchImageViewModelKT = ViewModelProvider(this,searchViewModelFactory)[SearchImageViewModelKT::class.java]

//        fromview(etSearchQuery)

        initSearchBox()
        initRecyclerView()


    }

    fun initSearchBox() {
        var job: Job? = null
        etSearchQuery.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Todo
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("Search", "onTextChanged: ${s.toString()}")
//                loadApiData(s.toString())
                job?.cancel()
                job = MainScope().launch {
                    delay(SEARCH_IMAGE_TIME_DELAY)
                    s.let {
                        if(it.toString().isNotEmpty()) {
                            searchImageViewModelKT.searchImages(s.toString())
                            searchPhotos(galleryRepository)
                        }
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
                //Todo
            }

        })

    }

    fun searchPhotos(galleryRepository: GalleryRepository) {
//        val galleryViewModelFactory = GalleryViewModelFactory(galleryRepository)
//        searchImageViewModelKT = ViewModelProvider(this,galleryViewModelFactory)[SearchImageViewModelKT::class.java]
//        searchImageViewModelKT.searchImages(text)
        searchImageViewModelKT.searchLatestImages.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    sProgressBar.visibility = View.GONE
                    tvShow.visibility = View.GONE
                    response.data?.photos?.let { galleryAdapter.setData(it.photo) }
                    recyclerViewSearchImage.visibility = View.VISIBLE
                }

                is Resource.Error -> {
                    sProgressBar.visibility = View.GONE
                    tvShow.visibility = View.VISIBLE
                    response.message?.let { message ->
                        Log.e("Gallery", "onCreate: ${message}", )
                    }
                }

                is Resource.Loading -> {
                    sProgressBar.visibility = View.VISIBLE
                    tvShow.visibility = View.GONE
                }
            }
        })
    }

    private fun initRecyclerView() {
        Log.d("Search", "initRecyclerView: ")
        recyclerViewSearchImage = findViewById(R.id.rv_search)
        galleryAdapter = GalleryAdapter(this, ArrayList())
        recyclerViewSearchImage.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@SearchImageActivity, 2)
            adapter = galleryAdapter
        }
    }

/*
    @SuppressLint("CheckResult")
    fun fromview(searchView: SearchView) {

        Observable.create<String> { emitter ->
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(!emitter.isDisposed) {
                        if (newText != null) {
                            emitter.onNext(newText)
                        }
                    }
                    return false
                }
            })
        }
                .debounce(1000, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
//                    loadApiData(it)
                },{
                        Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                })

    }
 */

/*
    fun loadApiData(input: String) {
        Log.d("Search", "loadApiData: $input")
        searchImageViewModel = ViewModelProvider(this).get(SearchImageViewModel::class.java)
        searchImageViewModel.getImageListObserver().observe(this, Observer<ApiResponse> {
            if (it != null) {
                Log.d("Search", "loadApiData: ${it}")
                sProgressBar.visibility = View.GONE
                recyclerViewSearchImage.visibility = View.VISIBLE
                it.photos.let { galleryAdapter.setData(it.photo) }
            } else {
                Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })
        if(!input.isEmpty() && input.length >= 3) {
            tvShow.visibility = View.GONE
            sProgressBar.visibility = View.VISIBLE
            searchImageViewModel.makeApiCall(input)
        } else {
            tvShow.visibility = View.VISIBLE
            recyclerViewSearchImage.visibility = View.GONE

        }
    }
   */

}