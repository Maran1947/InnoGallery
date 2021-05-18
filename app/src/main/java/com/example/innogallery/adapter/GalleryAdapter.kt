package com.example.innogallery.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.innogallery.ui.FullScreenActivity
import com.example.innogallery.model.Photo
import com.example.innogallery.databinding.ItemLayoutBinding

class GalleryAdapter(val context: Context, var gImages: List<Photo>): RecyclerView.Adapter<GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder( holder: GalleryViewHolder, position: Int) {
        val image = gImages[position]

        Glide.with(context).load(image.url_s).into(holder.binding.ivImage)
        holder.binding.ivImage.setOnClickListener {
            val i = Intent(context, FullScreenActivity::class.java)
            i.putExtra("imgPath",image.url_s)
            context.startActivity(i)
        }
    }
    override fun getItemCount(): Int {
        return gImages.size
    }

    fun setData(galleryList: List<Photo>) {
        this.gImages = galleryList
        notifyDataSetChanged()
    }

}

class GalleryViewHolder(val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root)