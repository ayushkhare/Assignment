package com.example.assignmentsephora.view.viewholder

import android.os.Build
import android.text.Html
import android.view.View
import com.example.assignmentsephora.databinding.ProductDescriptionBinding
import com.example.assignmentsephora.model.ProductDescription
import com.example.assignmentsephora.model.ProductDetail
import com.example.assignmentsephora.model.ProductHowTo
import com.example.assignmentsephora.view.BaseViewHolder

class DescriptionViewHolder(view: View) : BaseViewHolder<ProductDetail>(view) {
    private val binding = ProductDescriptionBinding.bind(view)

    override fun setData(item: ProductDetail) {
        if (item is ProductDescription) {
            binding.descriptionTitle.text = "DESCRIPTION"
            binding.productDescription.text =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(item.description, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(item.description)
                }
            binding.viewMore.text = "View more"
            binding.viewMore.setOnClickListener {
                val maxLines = binding.productDescription.maxLines
                if (maxLines == Int.MAX_VALUE) {
                    binding.productDescription.maxLines = 5
                    binding.viewMore.text = "View more"
                } else {
                    binding.productDescription.maxLines = Int.MAX_VALUE
                    binding.viewMore.text = "View less"
                }

            }
        } else if (item is ProductHowTo) {
            binding.viewMore.visibility = View.INVISIBLE
            binding.descriptionTitle.text = "HOW TO"
            binding.productDescription.text =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(item.howTo, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(item.howTo)
                }
        }
    }
}