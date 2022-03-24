package com.example.app



import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.app.data.JsonImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread


class DoodleImageDownload (){
    private val _images: MutableList<Bitmap> = mutableListOf()

    suspend fun loadImage(): MutableList<Bitmap> {
        val job = withContext(Dispatchers.IO) {
            for(i in 0 until 10 ) {
                var url = URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4XFa0i2H58farLmNpuChYmuADmvu3_dgE6aetcAmxhPAacH-32w")
//                val urlConnection = url.openConnection() as HttpURLConnection
//                urlConnection.doInput = true
//                urlConnection.connect()
                val inputStream = url.openStream()
                val bitmapImage = BitmapFactory.decodeStream(inputStream)
                _images.add(bitmapImage)
            }
        }
        return _images
    }
}