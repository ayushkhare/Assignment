package com.example.assignmentsephora.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignmentsephora.R

class ProductImageAdapter(
    private var images: List<String>,
    private var layoutId: Int,
) :
    RecyclerView.Adapter<ProductImageAdapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(imageUrl: String, position: Int) {
            Glide.with(view)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view.findViewById(R.id.image))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageUrl = images[position]
        holder.bindData(imageUrl, position)
    }

    fun setData(imagesUrls: List<String>) {
        images = imagesUrls
    }
}