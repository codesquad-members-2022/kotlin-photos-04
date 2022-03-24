package com.example.app.view

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.DoodleAdapter
import com.example.app.DoodleImageDownloader
import com.example.app.R
import com.example.app.data.JsonImage
import org.json.JSONObject

class DoodleActivity : AppCompatActivity() {

    private var jsonImageList = mutableListOf<JsonImage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doodle)

        val doodleView = findViewById<RecyclerView>(R.id.recycler_view_doodle)
        val doodleViewAdapter = DoodleAdapter()
        doodleView.adapter = doodleViewAdapter
        doodleView.layoutManager = GridLayoutManager(this, 4)

        extractDataFromJson(doodleViewAdapter)
    }

    private fun extractDataFromJson(adapter: DoodleAdapter) {
        val assetLoader = AssetLoader()
        val imageData = assetLoader.getJsonString(this, "Image.json")
        val doodleImageDownloader = DoodleImageDownloader()
        doodleImageDownloader.getDownloadedImages(jsonImageList, imageData, adapter)
    }

}