package com.example.assignmentsephora.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductResponse(
    @SerializedName("data")
    val productList: List<ProductData>,
    @SerializedName("meta")
    val productMeta: ProductMeta
)

data class ProductData(
    val id: String,
    val attributes: ProductAttributes
) : Serializable

data class ProductAttributes(
    val name: String,
    @SerializedName("display-price")
    val displayPrice: String,
    @SerializedName("display-original-price")
    val displayOriginalPrice: String,
    @SerializedName("sale-text")
    val saleText: String,
    @SerializedName("variants-count")
    val variantsCount: Int,
    @SerializedName("default-image-urls")
    val defaultImageUrls: List<String>,
    @SerializedName("brand-name")
    val brandName: String,
    @SerializedName("image-urls")
    val imageUrls: List<String>,
    @SerializedName("featured-image-urls")
    val thumbnailUrls: List<String>,
    val rating: Double,
    val ingredients: String,
    val description: String,
    @SerializedName("how-to-text")
    val howTo: String,
) : Serializable

data class ProductMeta(
    @SerializedName("total-pages")
    val totalPages: Int
)