package com.example.app.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.data.JsonImage

class ImageAdapter(private val jsonImageData: List<JsonImage>) :
    ListAdapter<JsonImage, ImageAdapter.ImageViewHolder>(ImageDiffCallback) {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.item_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)

        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageView.setImageBitmap(jsonImageData[position].image)
    }

    override fun getItemCount(): Int {
        return jsonImageData.size
    }
}

object ImageDiffCallback : DiffUtil.ItemCallback<JsonImage>() {
    override fun areItemsTheSame(oldItem: JsonImage, newItem: JsonImage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: JsonImage, newItem: JsonImage): Boolean {
        return oldItem == newItem
    }
}
