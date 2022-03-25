package com.example.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.viewmodel.DoodleViewModel
import com.example.app.R
import com.example.app.data.AssetLoader
import com.example.app.data.JsonImage

class DoodleActivity : AppCompatActivity() {

    private var jsonImageList = mutableListOf<JsonImage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doodle)

        val doodleViewModel: DoodleViewModel =
            ViewModelProvider(this).get(DoodleViewModel::class.java)
        val doodleView = findViewById<RecyclerView>(R.id.recycler_view_doodle)
        val doodleViewAdapter = DoodleAdapter()
        doodleView.adapter = doodleViewAdapter
        doodleView.layoutManager = GridLayoutManager(this, 4)

        extractDataFromJson(doodleViewAdapter)
    }

    private fun extractDataFromJson(adapter: DoodleAdapter) {
        val assetLoader = AssetLoader()
        val imageData = assetLoader.getJsonString(this, "Image.json") ?: ""
        val doodleImageDownloader = DoodleViewModel(this.application)
        doodleImageDownloader.getDownloadedImages(jsonImageList, imageData, adapter)
    }

}