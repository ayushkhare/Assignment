package com.example.assignmentsephora.view

import android.content.Context
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
    private lateinit var productClickListener: ProductClickListener
    private var currentPage = 1
    private var totalPagesAvailable = 1

    companion object {
        fun newInstance(): ProductListFragment {
            return ProductListFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ProductClickListener) {
            productClickListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater)
        setupFab()
        setupRecyclerView()
        setupViewModel()
        return binding.root
    }

    private fun setupFab() {
        binding.floatingActionButton.setOnClickListener {
            binding.productRecyclerView.smoothScrollToPosition(0)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory()
        ).get(ProductViewModel::class.java)

        viewModel.product.observe(viewLifecycleOwner, { it ->
            binding.swipeRefreshContainer.isRefreshing = false
            if (it != null) {
                totalPagesAvailable = it.productMeta.totalPages
                val oldCount = productDataList.size
                productDataList.addAll(it.productList)
                gridAdapter.setData(oldCount, productDataList.distinctBy { it.id })
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
        gridAdapter = ProductListGridAdapter(mutableListOf(), productClickListener)
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
        binding.swipeRefreshContainer.setOnRefreshListener {
            gridAdapter.clearData()
            productDataList.clear()
            currentPage = 1
            viewModel.getProductResponse(currentPage)
        }
    }
}

interface ProductClickListener {
    fun onClickProduct(product: ProductData)
}