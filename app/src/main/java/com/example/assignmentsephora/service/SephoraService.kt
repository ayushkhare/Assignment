package com.example.assignmentsephora.service

import com.example.assignmentsephora.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SephoraService {
    @GET(
        "products"
    )
    suspend fun getProducts(
        @Query(
            "page[number]"
        ) page: Int,
        @Query(
            "include"
        ) include: String = "featured_variant,featured_ad,brand,option_types.option_values,",
        @Query(
            "filter[landing_page]"
        ) filter: String = "sale",
        @Query(
            "sort"
        ) sort: String = "sales"
    ): Response<ProductResponse>
}