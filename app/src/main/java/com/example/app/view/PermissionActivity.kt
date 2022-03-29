package com.example.app.view

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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.app.R
import com.google.android.material.snackbar.Snackbar

class PermissionActivity : AppCompatActivity() {

    private lateinit var imageLoadingButton: Button

    @RequiresApi(Build.VERSION_CODES.M)
    private val permissionLauncher = requestPermissionLauncher()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        val permissionLayout: ConstraintLayout = findViewById(R.id.permission_layout)

        if (haveMediaPermission()) {
            goToMainActivity()
        } else {
            permissionLayout.visibility = View.VISIBLE
        }

        imageLoadingButton = findViewById(R.id.album_btn)
        imageLoadingButton.setOnClickListener {
            permissionLauncher.launch("android.permission.READ_EXTERNAL_STORAGE")
        }
    }

    private fun requestPermissionLauncher() =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                when {
                    isGranted -> {
                        goToMainActivity()
                    }
                    shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                        Snackbar.make(
                            imageLoadingButton,
                            "앱을 실행하기 위해서는 권한을 승인하시기 바랍니다.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    else -> {
                        goToSettings()
                    }
                }
            }
        }

    private fun goToMainActivity() {
        val loadImage = Intent(this, GalleryActivity::class.java)
        startActivity(loadImage)
    }

    private fun haveMediaPermission() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED

    private fun goToSettings() {
        val settingIntent = Intent().apply {
            this.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            this.data = Uri.fromParts("package", packageName, null)
        }
        startActivity(settingIntent)
    }

}