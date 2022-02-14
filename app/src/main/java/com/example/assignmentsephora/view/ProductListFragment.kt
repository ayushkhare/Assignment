package com.example.assignmentsephora.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentsephora.databinding.FragmentProductListBinding
import com.example.assignmentsephora.di.DependencyUtil
import com.example.assignmentsephora.model.ProductData
import com.example.assignmentsephora.viewmodel.ProductViewModel
import com.example.assignmentsephora.viewmodel.ViewModelFactory

class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    private lateinit var gridAdapter: ProductListGridAdapter
    private lateinit var viewModel: ProductViewModel
    private var productDataList: MutableList<ProductData> = mutableListOf()
    private var currentPage = 1
    private var totalPagesAvailable = 1

    companion object {
        fun newInstance(): ProductListFragment {
            return ProductListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater)
        setupRecyclerView()
        setupViewModel()
        return binding.root
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(DependencyUtil.provideSephoraRepository())
        ).get(ProductViewModel::class.java)

        viewModel.product.observe(viewLifecycleOwner, { it ->
            if (it != null) {
                totalPagesAvailable = it.productMeta.totalPages
                val oldCount = productDataList.size
                productDataList.addAll(it.productList)
                gridAdapter.setData(oldCount, productDataList)
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            binding.loader.visibility = when (it) {
                true -> View.VISIBLE
                false -> View.GONE
            }
        })

        viewModel.firstLoad.observe(viewLifecycleOwner, {
            binding.mainLoaderContainer.visibility = when (it) {
                true -> View.VISIBLE
                false -> View.GONE
            }
        })

        viewModel.getProductResponse(currentPage)
    }

    private fun setupRecyclerView() {
        val gridLayoutManager = GridLayoutManager(activity, 2)
        binding.productRecyclerView.layoutManager = gridLayoutManager
        gridAdapter = ProductListGridAdapter(mutableListOf())
        binding.productRecyclerView.adapter = gridAdapter
        val onScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (currentPage <= totalPagesAvailable && !recyclerView.canScrollVertically(1)) {
                    currentPage += 1
                    viewModel.getProductResponse(currentPage)
                }
            }
        }
        binding.productRecyclerView.addOnScrollListener(onScrollListener)
    }
}