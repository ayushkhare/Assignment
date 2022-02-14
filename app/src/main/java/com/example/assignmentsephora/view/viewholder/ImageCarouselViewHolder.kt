package com.example.assignmentsephora.view.viewholder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentsephora.R
import com.example.assignmentsephora.databinding.ViewImageCarouselBinding
import com.example.assignmentsephora.model.ProductDetail
import com.example.assignmentsephora.model.ProductDetailImage
import com.example.assignmentsephora.view.BaseViewHolder
import com.example.assignmentsephora.view.ProductImageAdapter

class ImageCarouselViewHolder(view: View) : BaseViewHolder<ProductDetail>(view) {

    private val binding = ViewImageCarouselBinding.bind(view)
    private val layoutManager =
        LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
    private val adapter = ProductImageAdapter(emptyList(), R.layout.product_image)
    private val snapHelper = PagerSnapHelper()

    override fun setData(item: ProductDetail) {
        if (item is ProductDetailImage) {
            binding.imageCarousel.layoutManager = layoutManager
            binding.imageCarousel.adapter = adapter;
            adapter.setData(item.imageUrls)
            snapHelper.attachToRecyclerView(binding.imageCarousel)
        }
    }
}