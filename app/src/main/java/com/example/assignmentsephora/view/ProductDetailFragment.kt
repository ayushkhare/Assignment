package com.example.assignmentsephora.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentsephora.databinding.FragmentProductDetailBinding
import com.example.assignmentsephora.di.DependencyUtil
import com.example.assignmentsephora.model.ProductData
import com.example.assignmentsephora.viewmodel.ProductViewModel
import com.example.assignmentsephora.viewmodel.ViewModelFactory

class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var viewModel: ProductViewModel
    private lateinit var adapter: ProductDetailAdapter

    companion object {
        fun newInstance(productData: ProductData): ProductDetailFragment {
            val args = Bundle()
            args.putSerializable("productData", productData)
            val fragment = ProductDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater)
        val data = requireArguments().getSerializable("productData") as ProductData
        setupViewModel(data)
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        adapter = ProductDetailAdapter(emptyList())
        binding.recyclerView.adapter = adapter
    }

    private fun setupViewModel(data: ProductData) {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory()
        ).get(ProductViewModel::class.java)

        viewModel.productDetail.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })

        viewModel.getProductAttributes(data)
    }
}