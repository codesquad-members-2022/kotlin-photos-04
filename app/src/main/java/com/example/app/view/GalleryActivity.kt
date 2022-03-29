package com.example.app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.data.GalleryImage
import com.example.app.viewmodel.GalleryViewModel
import com.google.android.material.appbar.MaterialToolbar

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAppbar()

        val galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        galleryViewModel.loadImageUri(this)
        val mediaStoreImageList = galleryViewModel.mediaStoreImageList

        val myRV = findViewById<RecyclerView>(R.id.recycler_view)
        val myAdapter = GalleryAdapter(mediaStoreImageList)
        myRV.adapter = myAdapter
    }

    private fun setAppbar() {
        val appBar = findViewById<MaterialToolbar>(R.id.appbar)
        appBar.setOnMenuItemClickListener {
            if (it.itemId == R.id.ic_permission) {
                val intent = Intent(this, DoodleActivity::class.java)
                startActivity(intent)
                return@setOnMenuItemClickListener true
            }
            false
        }
    }
}


