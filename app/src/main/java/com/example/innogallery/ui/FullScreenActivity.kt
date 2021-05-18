package com.example.innogallery.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.innogallery.R
import com.example.innogallery.adapter.GalleryAdapter


class FullScreenActivity : AppCompatActivity() {

    private lateinit var galleryAdapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)

        val image = findViewById<ImageView>(R.id.iv_image_full)

        val intent = getIntent()
        val imgPath: String? = intent.getStringExtra("imgPath")

         Glide.with(this).load(imgPath).into(image)

    }
}