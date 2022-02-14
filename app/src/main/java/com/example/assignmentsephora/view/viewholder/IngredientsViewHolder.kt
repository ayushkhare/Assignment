package com.example.assignmentsephora.view.viewholder

import android.view.View
import com.example.assignmentsephora.databinding.ProductIngredientsBinding
import com.example.assignmentsephora.model.ProductDetail
import com.example.assignmentsephora.model.ProductIngredients
import com.example.assignmentsephora.view.BaseViewHolder

class IngredientsViewHolder(view: View) : BaseViewHolder<ProductDetail>(view) {
    private val binding = ProductIngredientsBinding.bind(view)

    override fun setData(item: ProductDetail) {
        if (item is ProductIngredients) {
            binding.productIngredients.text = item.ingredients
            binding.viewMore.text = "View more"
            binding.viewMore.setOnClickListener {
                val maxLines = binding.productIngredients.maxLines
                if (maxLines == Int.MAX_VALUE) {
                    binding.productIngredients.maxLines = 5
                    binding.viewMore.text = "View more"
                } else {
                    binding.productIngredients.maxLines = Int.MAX_VALUE
                    binding.viewMore.text = "View less"
                }
            }
        }
    }
}