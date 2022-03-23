package com.example.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.R
import com.example.app.data.JsonImage
import org.json.JSONObject

class DoodleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doodle)

        val jsonImageList: MutableList<JsonImage> = mutableListOf()

        val assetLoader = AssetLoader()
        val imageData = assetLoader.getJsonString(this, "Image.json")

        if (!imageData.isNullOrEmpty()) {
            val jsonObject = JSONObject(imageData)
            val jsonList = jsonObject.getJSONArray("DownloadedImage")

            for (i in 0 until jsonList.length()) {
                val imageObject = jsonList.getJSONObject(i)
                jsonImageList.add(
                    JsonImage(
                        imageObject.getString("title"),
                        imageObject.getString("image"),
                        imageObject.getString("date")
                    )
                )
            }
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val menuInflater = menuInflater
//        menuInflater.inflate(R.menu.menu_doodle_appbar, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.menu_download -> {
//                Snackbar.make(, "download Clicked", Snackbar.LENGTH_LONG).show()
//                true
//            }
//            R.id.menu_close -> {
//
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}