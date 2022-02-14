package com.example.assignmentsephora.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignmentsephora.model.ProductResponse
import com.example.assignmentsephora.service.SephoraRepository
import kotlinx.coroutines.*

class ProductViewModel(private val repository: SephoraRepository) : ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val product: MutableLiveData<ProductResponse> = MutableLiveData()
    val firstLoad: MutableLiveData<Boolean> = MutableLiveData()

    private var job: Job? = null

    fun getProductResponse(currentPage: Int) {
        job = CoroutineScope(Dispatchers.Main).launch {
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

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}