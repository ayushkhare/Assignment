package com.example.assignmentsephora.view.viewholder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentsephora.R
import com.example.assignmentsephora.databinding.ViewImageCarouselBinding
import com.example.assignmentsephora.model.ProductDetail
import com.example.assignmentsephora.model.ProductDetailThumbnail
import com.example.assignmentsephora.view.BaseViewHolder
import com.example.assignmentsephora.view.ProductImageAdapter

class ThumbnailCarouselViewHolder(view: View) : BaseViewHolder<ProductDetail>(view) {
    private val binding = ViewImageCarouselBinding.bind(view)
    private val layoutManager =
        LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
    private val adapter =
        ProductImageAdapter(emptyList(), R.layout.product_image_thumbnail)

    override fun setData(item: ProductDetail) {
        if (item is ProductDetailThumbnail) {
            binding.imageCarousel.layoutManager = layoutManager
            binding.imageCarousel.adapter = adapter;
            adapter.setData(item.imageUrls)
        }
    }
}