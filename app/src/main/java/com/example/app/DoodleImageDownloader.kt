package com.example.app

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.app.data.JsonImage
import kotlinx.coroutines.*
import org.json.JSONObject

import java.net.URL


class DoodleImageDownloader {

    private suspend fun loadImage(uri: String): Bitmap? {
        val url = URL(uri)
        var bitmap: Bitmap? = null
        withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val inputStream = url.openStream()
                bitmap = BitmapFactory.decodeStream(inputStream)
            }.getOrNull()
        }
        return bitmap
    }

    fun getDownloadedImages(
        jsonImageList: MutableList<JsonImage>, imageData: String?, adapter: DoodleAdapter
    ) {
        val scope = CoroutineScope(Dispatchers.Main)
        val jsonObject = JSONObject(imageData)
        val jsonList = jsonObject.getJSONArray("DownloadedImage")

        runBlocking {
            scope.launch {
                for (i in 0 until jsonList.length()) {
                    val imageObject = jsonList.getJSONObject(i)
                    val jsonBitmapImage = loadImage(imageObject.getString("image"))
                    jsonImageList.add(
                        JsonImage(
                            imageObject.getString("title"),
                            jsonBitmapImage,
                            imageObject.getString("date")
                        )
                    )
                }
                adapter.submitList(jsonImageList)
            }
        }
    }
}