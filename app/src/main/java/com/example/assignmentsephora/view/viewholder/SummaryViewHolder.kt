package com.example.assignmentsephora.view.viewholder

import android.graphics.Paint
import android.view.View
import com.example.assignmentsephora.databinding.ProductSummaryBinding
import com.example.assignmentsephora.model.ProductDetail
import com.example.assignmentsephora.model.ProductDetailSummary
import com.example.assignmentsephora.view.BaseViewHolder

class SummaryViewHolder(view: View) : BaseViewHolder<ProductDetail>(view) {
    private val binding = ProductSummaryBinding.bind(view)

    override fun setData(item: ProductDetail) {
        if (item is ProductDetailSummary) {
            binding.brandName.text = item.brandName
            binding.name.text = item.name
            binding.productOriginalPrice.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                text = item.originalPrice
            }
            binding.productDiscountedPrice.text = item.discountedPrice
            binding.productDiscount.text = "(" + item.saleText + ")"
            binding.rating.rating = item.rating.toFloat()
        }
    }
}