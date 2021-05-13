package com.example.innogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.innogallery.adapter.GalleryAdapter
import com.example.innogallery.repository.GalleryRepository
import com.example.innogallery.util.Resource
import com.example.innogallery.viewmodel.GalleryViewModel
import com.example.innogallery.viewmodel.GalleryViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var galleryAdapter: GalleryAdapter
    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)
        initRecyclerView()

        val galleryRepository = GalleryRepository()
        val galleryViewModelFactory = GalleryViewModelFactory(galleryRepository)
        galleryViewModel = ViewModelProvider(this,galleryViewModelFactory)[GalleryViewModel::class.java]
        galleryViewModel.getImages()
        galleryViewModel.latestImages.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    response.data?.photos?.let { galleryAdapter.setData(it.photo) }
                    recyclerView.visibility = View.VISIBLE
                }

                is Resource.Error -> {
                    progressBar.visibility = View.GONE
                    response.message?.let { message ->
                        Log.e("Gallery", "onCreate: ${message}", )
                    }
                }

                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        galleryAdapter = GalleryAdapter(this, ArrayList())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity,2)
            adapter = galleryAdapter
        }
    }
}