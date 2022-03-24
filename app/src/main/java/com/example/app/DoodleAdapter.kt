package com.example.app

import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.app.data.JsonImage
import kotlinx.coroutines.*

class DoodleAdapter:
    ListAdapter<JsonImage, DoodleAdapter.DoodleAdapterViewHolder>(ImageDiffCallback) {

    private var bitmapImages: MutableList<Bitmap> = mutableListOf()

    inner class DoodleAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val doodleImageView: ImageView = view.findViewById<ImageView>(R.id.doodle_item_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoodleAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doodle_view_item, parent, false)
        return DoodleAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoodleAdapterViewHolder, position: Int) {
        holder.doodleImageView.setImageBitmap(bitmapImages[position])
    }

    override fun getItemCount(): Int {
        return 10
    }



}
