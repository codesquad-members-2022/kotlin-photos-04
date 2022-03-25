package com.example.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app.data.JsonImage
import kotlinx.coroutines.*

class DoodleAdapter :
    ListAdapter<JsonImage, DoodleAdapter.DoodleAdapterViewHolder>(ImageDiffCallback) {

    inner class DoodleAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val doodleImageView: ImageView = view.findViewById(R.id.doodle_item_view)
        private val doodleProgressBar: ProgressBar = view.findViewById(R.id.doodle_progressbar)
        private var doodleImage: JsonImage? = null

        fun bind(doodleImage: JsonImage) {
            this.doodleImage = doodleImage
            doodleProgressBar.visibility = View.INVISIBLE
            doodleImageView.setImageBitmap(doodleImage.image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoodleAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.doodle_view_item, parent, false)
        return DoodleAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoodleAdapterViewHolder, position: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            holder.bind(getItem(position))
            println("$position")
        }
    }

}
