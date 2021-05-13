package com.example.innogallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.innogallery.adapter.GalleryAdapter
import com.example.innogallery.repository.GalleryRepository
import com.example.innogallery.util.Resource
import com.example.innogallery.viewmodel.GalleryViewModel
import com.example.innogallery.viewmodel.GalleryViewModelFactory

class FullScreenActivity : AppCompatActivity() {

    private lateinit var galleryAdapter: GalleryAdapter
    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)

        val image = findViewById<ImageView>(R.id.iv_image_full)

        val intent = getIntent()
        val imgPath: String? = intent.getStringExtra("imgPath")

         Glide.with(this).load(imgPath).into(image)

    }
}