package com.example.assignmentsephora.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentsephora.R
import com.example.assignmentsephora.model.ProductDetail
import com.example.assignmentsephora.model.ProductDetailViewType
import com.example.assignmentsephora.view.viewholder.*

class ProductDetailAdapter(private var productDetails: List<ProductDetail>) :
    RecyclerView.Adapter<BaseViewHolder<ProductDetail>>() {

    override fun getItemViewType(position: Int): Int {
        return productDetails[position].viewType.value
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ProductDetail> {
        val viewHolder: BaseViewHolder<ProductDetail> = when (viewType) {
            ProductDetailViewType.ImageCarousel.value -> {
                ImageCarouselViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_image_carousel, parent, false)
                )
            }
            ProductDetailViewType.ThumbnailCarousel.value -> {
                ThumbnailCarouselViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_image_carousel, parent, false)
                )
            }
            ProductDetailViewType.Summary.value -> {
                SummaryViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.product_summary, parent, false)
                )
            }
            ProductDetailViewType.Separator.value -> {
                SeparatorViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.separator, parent, false)
                )
            }
            ProductDetailViewType.Ingredients.value -> {
                IngredientsViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.product_ingredients, parent, false)
                )
            }
            ProductDetailViewType.Description.value -> {
                DescriptionViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.product_description, parent, false)
                )
            }
            ProductDetailViewType.HowTo.value -> {
                DescriptionViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.product_description, parent, false)
                )
            }
            else -> {
                ImageCarouselViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_image_carousel, parent, false)
                )
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ProductDetail>, position: Int) {
        val productDetail = productDetails[position]
        holder.setData(productDetail)
    }

    fun setData(data: List<ProductDetail>) {
        productDetails = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return productDetails.size
    }
}