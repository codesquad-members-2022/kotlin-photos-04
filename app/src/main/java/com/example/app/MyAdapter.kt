package com.example.app

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val data: List<String>) : RecyclerView.Adapter<MyAdapter.RectangleViewHolder>() {

    class RectangleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.item_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RectangleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)

        return RectangleViewHolder(view)
    }

    override fun onBindViewHolder(holder: RectangleViewHolder, position: Int) {
        holder.imageView.setBackgroundColor(Color.parseColor(data[position]))
    }

    override fun getItemCount(): Int {
        return data.size
    }
}