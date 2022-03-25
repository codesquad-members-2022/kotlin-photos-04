package com.example.app.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.data.JsonImage
import com.example.app.databinding.DoodleViewItemBinding
import kotlinx.coroutines.*

class DoodleAdapter :
    ListAdapter<JsonImage, DoodleAdapter.DoodleAdapterViewHolder>(ImageDiffCallback) {

    inner class DoodleAdapterViewHolder(private val doodleBinding: DoodleViewItemBinding) :
        RecyclerView.ViewHolder(doodleBinding.root) {

        private var doodleImage: JsonImage? = null

        fun bind(doodleImage: JsonImage) {
            this.doodleImage = doodleImage
            doodleBinding.doodleProgressbar.visibility = View.INVISIBLE
            doodleBinding.doodleItemView.setImageBitmap(doodleImage.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoodleAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val doodleBinding: DoodleViewItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.doodle_view_item, parent, false)
        return DoodleAdapterViewHolder(doodleBinding)

    }

    override fun onBindViewHolder(holder: DoodleAdapterViewHolder, position: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            holder.bind(getItem(position))
        }
    }

}
