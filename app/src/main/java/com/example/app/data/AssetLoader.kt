package com.example.app.data

import android.content.Context
import com.example.app.view.DoodleAdapter
import com.example.app.viewmodel.DoodleViewModel

class AssetLoader {

    fun getJsonString(context: Context, fileName: String):String? {
        return kotlin.runCatching {
            loadAsset(context, fileName)
        }.getOrNull()
    }

    private fun loadAsset(context: Context, fileName: String): String {
        return context.assets.open(fileName).use { inputStream ->
            val size = inputStream.available()
            val bytes = ByteArray(size)
            inputStream.read(bytes)
            String(bytes)
        }
    }

    private fun extractDataFromJson(adapter: DoodleAdapter) {
        val assetLoader = AssetLoader()
        val imageData = assetLoader.getJsonString(this, "Image.json") ?: ""
        val doodleImageDownloader = DoodleViewModel(this.application)
        doodleImageDownloader.getDownloadedImages(jsonImageList, imageData, adapter)
    }

}