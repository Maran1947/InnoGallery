package com.example.innogallery.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.innogallery.*

class GalleryAdapter(val context: Context, var gImages: List<Photo>): RecyclerView.Adapter<GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val image = gImages[position]

        Glide.with(context).load(image.url_s).into(holder.imageView)
        holder.imageView.setOnClickListener {
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

class GalleryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.iv_image)
}
