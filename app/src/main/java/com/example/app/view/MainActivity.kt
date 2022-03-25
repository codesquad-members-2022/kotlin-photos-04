package com.example.app.view

import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.data.Image
import com.example.app.data.JsonImage
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private val mediaStoreImageList = mutableListOf<Image>()

    private val jsonImageList = mutableListOf<JsonImage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAppbar()
        loadImageUri()

        val myRV = findViewById<RecyclerView>(R.id.recycler_view)
        val myAdapter = ImageAdapter(jsonImageList)
        myRV.adapter = myAdapter
    }


    private fun loadImageUri() {
        val projection = arrayOf(MediaStore.Images.Media._ID)
        applicationContext.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                mediaStoreImageList += Image(id, contentUri)
            }
        }
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


