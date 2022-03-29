package com.example.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.app.viewmodel.DoodleViewModel
import com.example.app.R
import com.example.app.data.JsonImage
import com.example.app.databinding.ActivityDoodleBinding

class DoodleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoodleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_doodle)

        val doodleViewAdapter = DoodleAdapter()
        binding.recyclerViewDoodle.also { view ->
            view.adapter = doodleViewAdapter
            view.layoutManager = GridLayoutManager(this, 4)
        }

        val doodleViewModel: DoodleViewModel =
            ViewModelProvider(this).get(DoodleViewModel::class.java)

        doodleViewModel.images.observe(this, Observer<List<JsonImage>> { images ->
            doodleViewAdapter.submitList(images)
        })
    }
}