package com.example.app

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.*
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var imageLoadingButton: Button

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val test = requestPermissionLauncher()
        // 미션을 위해 잠시 주석 처리
        /*val colorData = Color.make(mutableListOf()).fillList()
        val myRV = findViewById<RecyclerView>(R.id.recycler_view)
        val myAdapter = MyAdapter(colorData)
        myRV.adapter = myAdapter*/
        imageLoadingButton = findViewById(R.id.album_btn)
        imageLoadingButton.setOnClickListener {
            requestPermissionLauncher()
            loadImageFromContent(test)
        }
    }

    private fun requestPermissionLauncher() =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                val loadImage = Intent(Intent.ACTION_GET_CONTENT).apply {
                    this.type = "image/*"
                }
                startActivity(loadImage)
            } else {
                Snackbar.make(imageLoadingButton, "request denied", Snackbar.LENGTH_LONG).show()
            }
        }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun loadImageFromContent(launcher: ActivityResultLauncher<String>) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_MEDIA_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Snackbar.make(imageLoadingButton, "permission Granted", Snackbar.LENGTH_LONG).show()
            launcher.launch("android.permission.ACCESS_MEDIA_LOCATION")
        } else {
            requestMediaPermission(launcher)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestMediaPermission(launcher: ActivityResultLauncher<String>) {
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_MEDIA_LOCATION)) {
            Snackbar.make(
                imageLoadingButton, "permission 2nd denied", Snackbar.LENGTH_LONG
            ).show()

            launcher.launch("android.permission.ACCESS_MEDIA_LOCATION")

//            val settingIntent = Intent().apply {
//                this.action = ACTION_APPLICATION_DETAILS_SETTINGS
//            }
//            startActivity(settingIntent)
        } else {
            launcher.launch("android.permission.ACCESS_MEDIA_LOCATION")
        }
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when(requestCode) {
//            0 -> {
//                if(grantResults.isNotEmpty()) {
//                    var isAllGranted = true
//                    grantResults.forEach { isGranted ->
//                        if(isGranted != PackageManager.PERMISSION_GRANTED) {
//                            isAllGranted = false
//                            return@forEach
//                        }
//                    }
//                    if(isAllGranted) {
//                        val loadImage = Intent(Intent.ACTION_GET_CONTENT).apply {
//                            this.type = "image/*"
//                        }
//                        startActivity(loadImage)
//                    } else {
//                        if(!ActivityCompat.shouldShowRequestPermissionRationale(this,
//                            Manifest.permission.ACCESS_MEDIA_LOCATION)) {
//                            ActivityCompat.requestPermissions(this, permissions, 0)
//                        }
//                        else {
//                            val settingIntent = Intent().apply {
//                                this.action = ACTION_APPLICATION_DETAILS_SETTINGS
//                            }
//                            startActivity(settingIntent)
//                        }
//                    }
//                }
//            }
//        }
//    }
}
