package com.example.assignmentsephora.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentsephora.R
import com.example.assignmentsephora.databinding.ProductGridItemBinding
import com.example.assignmentsephora.model.ProductData

class ProductListGridAdapter(private var productList: List<ProductData>) :
    RecyclerView.Adapter<ProductListGridAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var data: ProductData? = null
        private val viewBinding = ProductGridItemBinding.bind(view)

        fun bindData(productData: ProductData) {
            viewBinding.textView.text = productData.id
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

    fun setData(data: List<ProductData>) {
        productList = data
        notifyDataSetChanged()
    }
}