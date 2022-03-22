package com.example.app

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class PermissionActivity : AppCompatActivity() {

    private lateinit var imageLoadingButton: Button
    private val permissionLauncher = requestPermissionLauncher()
    private val permissionLauncherForSecond = requestPermissionLauncherSecond()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        imageLoadingButton = findViewById(R.id.album_btn)
        imageLoadingButton.setOnClickListener {
            loadImageFromContent()
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

    private fun requestPermissionLauncherSecond() =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                val loadImage = Intent(Intent.ACTION_GET_CONTENT).apply {
                    this.type = "image/*"
                }
                startActivity(loadImage)
            } else {
                imageLoadingButton.showSnackbar(
                    "이미지를 불러오기 위해 권한을 승인하시기 바랍니다",
                    Snackbar.LENGTH_SHORT,
                    "설정창으로 가기"
                ) {
                    val settingIntent = Intent().apply {
                        this.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        this.data = Uri.fromParts("package", packageName, null)
                    }
                    startActivity(settingIntent)
                }
            }
        }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun loadImageFromContent() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_MEDIA_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Snackbar.make(imageLoadingButton, "permission Granted", Snackbar.LENGTH_LONG).show()
            permissionLauncher.launch("android.permission.ACCESS_MEDIA_LOCATION")
        } else {
            requestMediaPermission()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestMediaPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_MEDIA_LOCATION)) {
            permissionLauncherForSecond.launch("android.permission.ACCESS_MEDIA_LOCATION")
        } else {
            permissionLauncher.launch("android.permission.ACCESS_MEDIA_LOCATION")
        }
    }

}

fun View.showSnackbar(
    msg: String,
    length: Int,
    actionMessage: CharSequence?,
    action: (View) -> Unit
) {
    val snackbar = Snackbar.make(this, msg, length)
    if (actionMessage != null) {
        snackbar.setAction(actionMessage) {
            action(this)
        }.show()
    }
}
