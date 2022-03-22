package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAppbar()

        val colorData = Color.make(mutableListOf()).fillList()
        val myRV = findViewById<RecyclerView>(R.id.recycler_view)
        val myAdapter = MyAdapter(colorData)
        myRV.adapter = myAdapter

    }

    private fun setAppbar() {
        val appBar = findViewById<MaterialToolbar>(R.id.appbar)
        appBar.setOnMenuItemClickListener {
            if (it.itemId == R.id.ic_permission) {
                val intent = Intent(this, PermissionActivity::class.java)
                startActivity(intent)
                return@setOnMenuItemClickListener true
            }
            false
        }
    }


}
