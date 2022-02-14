package com.example.assignmentsephora

import com.example.assignmentsephora.model.ProductResponse
import com.example.assignmentsephora.service.SephoraService
import retrofit2.Response

class MockSephoraService : SephoraService {
    override suspend fun getProducts(
        page: Int,
        include: String,
        filter: String,
        sort: String
    ): Response<ProductResponse> {
        return Response.success(testResponse)
    }
}