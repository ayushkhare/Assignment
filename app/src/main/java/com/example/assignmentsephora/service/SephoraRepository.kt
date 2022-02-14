package com.example.assignmentsephora.service

import com.example.assignmentsephora.model.ProductResponse
import kotlinx.coroutines.coroutineScope

class SephoraRepository(private val service: SephoraService) {
    suspend fun loadProducts(page: Int, updateResult: suspend (ProductResponse?) -> Unit) {
        coroutineScope {
            val data = service
                .getProducts(page)
                .body()

            updateResult(data)
        }
    }
}