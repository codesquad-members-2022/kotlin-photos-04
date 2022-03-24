package com.example.app

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app.data.JsonImage
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

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
        val url =
            URL(jsonImageData[position].uri)
        var bitmapImage: Bitmap? = null
        val thread = Thread {
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doInput = true
            urlConnection.connect()
            val inputStream = urlConnection.inputStream
            bitmapImage = BitmapFactory.decodeStream(inputStream)
        }
        thread.start()
        thread.join()
        holder.imageView.setImageBitmap(bitmapImage)

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
