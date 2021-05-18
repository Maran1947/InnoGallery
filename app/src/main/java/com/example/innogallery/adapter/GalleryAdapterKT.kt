package com.example.innogallery.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.innogallery.ui.FullScreenActivity
import com.example.innogallery.model.Photo
import com.example.innogallery.R

class GalleryAdapterKT(val context: Context): PagingDataAdapter<Photo,GalleryViewHolderKT>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolderKT {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return GalleryViewHolderKT(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolderKT, position: Int) {
        val image = getItem(position)

        val imageView = holder.itemView.findViewById<ImageView>(R.id.iv_image)

        if (image != null) {
            Glide.with(context).load(image.url_s).into(imageView)
        }
        imageView.setOnClickListener {
            val i = Intent(context, FullScreenActivity::class.java)
            if (image != null) {
                i.putExtra("imgPath",image.url_s)
            }
            context.startActivity(i)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }

        }
    }

}

class GalleryViewHolderKT(itemView: View): RecyclerView.ViewHolder(itemView)