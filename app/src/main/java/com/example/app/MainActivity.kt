package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myRV = findViewById<RecyclerView>(R.id.recycler_view)
        val myAdapter = MyAdapter()
        myRV.adapter = myAdapter
    }
}