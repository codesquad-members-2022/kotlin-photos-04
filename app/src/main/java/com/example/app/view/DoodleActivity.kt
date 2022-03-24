package com.example.app.view

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.DoodleAdapter
import com.example.app.DoodleImageDownload
import com.example.app.R
import com.example.app.data.JsonImage
import com.example.app.viewmodel.DoodleActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject

class DoodleActivity : AppCompatActivity() {
    private var bitmapImages = mutableListOf<Bitmap>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doodle)

        val doodleView = findViewById<RecyclerView>(R.id.recycler_view_doodle)
        val doodleViewAdapter = DoodleAdapter()
        doodleView.adapter = doodleViewAdapter
        doodleView.layoutManager = GridLayoutManager(this, 4)

        getDownloadedImages(doodleViewAdapter)


    }

    private fun getDownloadedImages(adapter: DoodleAdapter) {
        val scope = CoroutineScope(Dispatchers.Default)
        val imageList = DoodleImageDownload()
        runBlocking {
            scope.launch {
                bitmapImages = imageList.loadImage()
                adapter.submitList()
            }
        }
    }
}