package com.example.assignmentsephora

import com.example.assignmentsephora.model.ProductAttributes
import com.example.assignmentsephora.model.ProductData
import com.example.assignmentsephora.model.ProductMeta
import com.example.assignmentsephora.model.ProductResponse

val testProductMeta: ProductMeta = ProductMeta(10)

val testProductAttributes: ProductAttributes =
    ProductAttributes(
        "test",
        "20",
        "30",
        "$10 OFF",
        2,
        listOf("images.png"),
        "test branch",
        listOf("images.png"),
        listOf("images.png"),
        4.0,
        "test ingredients",
        "test description",
        "test how to"
    )

val testProductList: List<ProductData> = listOf(ProductData("1", testProductAttributes))

val testResponse: ProductResponse = ProductResponse(testProductList, testProductMeta)