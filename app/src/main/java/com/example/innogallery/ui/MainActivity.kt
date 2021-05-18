package com.example.innogallery.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.innogallery.adapter.GalleryAdapter
import com.example.innogallery.databinding.ActivityMainBinding

import com.example.innogallery.repository.GalleryRepository
import com.example.innogallery.util.Resource
import com.example.innogallery.viewmodel.GalleryViewModelFactory
import com.example.innogallery.viewmodel.GalleryViewModelKT

class MainActivity : AppCompatActivity() {

    private lateinit var galleryAdapter: GalleryAdapter
//    private lateinit var galleryAdapterKT: GalleryAdapterKT

    private lateinit var galleryViewModelKT: GalleryViewModelKT
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivSearchImg.setOnClickListener {
            val i = Intent(this, SearchImageActivity::class.java)
            startActivity(i)
        }
//

//        val galleryViewModel by viewModels<GalleryViewModel>()
//        galleryAdapter = GalleryAdapter(this,ArrayList())

        val galleryRepository = GalleryRepository()


        initRecyclerView()
//        loadApiData(galleryViewModel)

//        initViewModel(galleryRepository)

        val galleryViewModelFactory = GalleryViewModelFactory(galleryRepository)
        galleryViewModelKT = ViewModelProvider(this,galleryViewModelFactory)[GalleryViewModelKT::class.java]
        galleryViewModelKT.getImages()
        galleryViewModelKT.latestImages.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvError.visibility = View.GONE
                    response.data?.photos?.let { galleryAdapter.setData(it.photo) }
                    binding.recyclerView.visibility = View.VISIBLE
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvError.visibility = View.VISIBLE
                    response.message?.let { message ->
                        Log.e("Gallery", "onCreate: ${message}", )
                    }
                }

                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                }
            }
        })

//        galleryViewModelKT.photos.observe(this) {
//            galleryAdapterKT.submitData(lifecycle,it)
//        }

    }

    private fun initRecyclerView() {
        galleryAdapter = GalleryAdapter(this, ArrayList())
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity,2)
            adapter = galleryAdapter
        }
    }

    // used for Rxjava
/*@ExperimentalCoroutinesApi
@ExperimentalCoroutinesApi
fun loadApiData(galleryViewModel: GalleryViewModel) {
//        Log.d("Search", "loadApiData: ${galleryViewModel.trendingImages}")
//        val galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        galleryViewModel.imageList.observe(this, Observer {
            if (it != null) {
                Log.d("Search", "loadApiData: ${it}")
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                it.photos.let { galleryAdapter.setData(it.photo) }
            } else {
                tvError.visibility = View.VISIBLE
                tvError.text = "Error in fetching"
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
//            it.map { it -> Log.d("TAG", "loadApiData Photo: ${it.url_s}") }
//            Log.d("TAG", "loadApiData: 2
//            galleryAdapter.submitData(lifecycle, it)

        })
    }*/


/*    @ExperimentalCoroutinesApi
    private fun initViewModel(galleryRepository: GalleryRepository) {
//        val galleryViewModelKT = ViewModelProvider(this).get(GalleryViewModelKT::class.java)
        val galleryViewModelFactory = GalleryViewModelFactory(galleryRepository)
        galleryViewModelKT = ViewModelProvider(this,galleryViewModelFactory)[GalleryViewModelKT::class.java]
        lifecycleScope.launch {
            galleryViewModelKT.getPhotos().collectLatest {
                galleryAdapterKT.submitData(it)
            }
        }
    }*/

}