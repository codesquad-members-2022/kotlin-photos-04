package com.example.app.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app.data.AssetLoader
import com.example.app.data.JsonImage
import com.example.app.view.DoodleAdapter
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.URL

class DoodleViewModel(application: Application) : AndroidViewModel(application) {

    private var jsonImageList = mutableListOf<JsonImage>()

    private val _images = MutableLiveData<List<JsonImage>>()
    val images: LiveData<List<JsonImage>> get() = _images

    fun extractDataFromJson(adapter: DoodleAdapter) {
        val assetLoader = AssetLoader()
        val imageData = assetLoader.getJsonString(getApplication(), "Image.json") ?: ""
        getDownloadedImages(jsonImageList, imageData, adapter)
    }

    private fun loadImage(uri: String): Bitmap? {
        val url = URL(uri)
        val bitmap = kotlin.runCatching {
            val inputStream = url.openStream()
            BitmapFactory.decodeStream(inputStream)
        }.getOrNull()
        return bitmap
    }

    private fun getDownloadedImages(
        jsonImageList: MutableList<JsonImage>, imageData: String, adapter: DoodleAdapter
    ) {
        val scope = CoroutineScope(Dispatchers.IO)
        val jsonObject = JSONObject(imageData)
        val jsonList = jsonObject.getJSONArray("DownloadedImage")

        for (i in 0 until jsonList.length()) {
            scope.launch {
                val imageObject = jsonList.getJSONObject(i)
                val uri = imageObject.getString("image")
                val jsonBitmapImage: Bitmap? = loadImage(uri)

                scope.launch(Dispatchers.Default) {
                    jsonImageList.add(
                        JsonImage(
                            imageObject.getString("title"),
                            jsonBitmapImage,
                            imageObject.getString("date")
                        )
                    )
                }

                scope.launch(Dispatchers.Main) {
                    _images.postValue(jsonImageList)
                }
            }
        }
    }
}





