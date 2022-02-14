package com.example.assignmentsephora.view

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignmentsephora.R
import com.example.assignmentsephora.databinding.ProductGridItemBinding
import com.example.assignmentsephora.model.ProductData

class ProductListGridAdapter(private var productList: List<ProductData>) :
    RecyclerView.Adapter<ProductListGridAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var data: ProductData? = null
        private val viewBinding = ProductGridItemBinding.bind(view)

        fun bindData(productData: ProductData) {
            val attributes = productData.attributes
            viewBinding.productBrand.text = attributes.brandName
            viewBinding.productName.text = attributes.name
            viewBinding.productOriginalPrice.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                text = attributes.displayOriginalPrice
            }
            viewBinding.productDiscountedPrice.text = attributes.displayPrice
            viewBinding.productDiscount.text = "(" + attributes.saleText + ")"
            viewBinding.productVariantsCount.text =
                attributes.variantsCount.toString() + " variants"
            // load image
            Glide.with(itemView)
                .load(attributes.defaultImageUrls.first())
                .placeholder(R.drawable.ic_launcher_background)
                .into(viewBinding.productImage)
            viewBinding.productImage
            viewBinding.rating.rating = attributes.rating.toFloat()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_grid_item, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productData = productList[position]
        holder.bindData(productData)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setData(oldCount: Int, data: List<ProductData>) {
        productList = data
        notifyItemRangeInserted(oldCount, data.size)
    }
}