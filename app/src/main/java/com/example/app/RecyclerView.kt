package com.example.app

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView


class CustomRecyclerView(private val data: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_element, parent, false)
        return RectangleViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as RectangleViewHolder
        holder.imageView.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24)
        holder.imageView.setBackgroundColor(Color.parseColor(data[position]))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RectangleViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.color_rectangle)
    }
}