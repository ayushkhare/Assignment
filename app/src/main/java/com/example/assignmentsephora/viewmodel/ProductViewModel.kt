package com.example.assignmentsephora.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignmentsephora.model.*
import com.example.assignmentsephora.service.SephoraRepository
import kotlinx.coroutines.*

class ProductViewModel(private val repository: SephoraRepository) : ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val product: MutableLiveData<ProductResponse> = MutableLiveData()
    val firstLoad: MutableLiveData<Boolean> = MutableLiveData()
    val productDetail: MutableLiveData<List<ProductDetail>> = MutableLiveData()

    private var job: Job? = null

    fun getProductResponse(currentPage: Int) {
        job = CoroutineScope(Dispatchers.IO).launch {
            loading.postValue(true)
            repository.loadProducts(currentPage) { response ->
                withContext(Dispatchers.Main) {
                    product.postValue(response)
                    loading.postValue(false)
                    if (firstLoad.value == true) {
                        firstLoad.postValue(false)
                    }
                }
            }
        }
    }

    fun getProductAttributes(productData: ProductData) {
        var productDetailList: MutableList<ProductDetail> = mutableListOf()
        val attributes = productData.attributes
        // image carousel
        var productDetailImage = ProductDetailImage(attributes.imageUrls)
        // thumbnail carousel
        val productDetailThumbnail = ProductDetailThumbnail(attributes.thumbnailUrls)
        // summary price/brand
        val productDetailSummary = ProductDetailSummary(
            attributes.brandName,
            attributes.name,
            attributes.displayOriginalPrice,
            attributes.displayPrice,
            attributes.saleText,
            attributes.rating
        )
        // description
        val productDetailDescription =
            ProductDescription(attributes.description.replace("\n", "<br>"))
        // ingredients
        val productDetailIngredients = ProductIngredients(attributes.ingredients)
        // how to
        val productDetailHowTo = ProductHowTo(attributes.howTo.replace("\n", "<br>"))
        productDetailList.add(productDetailImage)
        productDetailList.add(productDetailThumbnail)
        productDetailList.add(productDetailSummary)
        productDetailList.add(ProductSeparator())
        productDetailList.add(productDetailDescription)
        productDetailList.add(ProductSeparator())
        productDetailList.add(productDetailIngredients)
        productDetailList.add(ProductSeparator())
        productDetailList.add(productDetailHowTo)
        productDetail.postValue(productDetailList)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}