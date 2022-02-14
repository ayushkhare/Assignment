package com.example.assignmentsephora.model

enum class ProductDetailViewType(val value: Int) {
    ImageCarousel(0),
    ThumbnailCarousel(1),
    Summary(2),
    Separator(3),
    Description(4),
    Ingredients(5),
    HowTo(6),
}

class ProductDetailImage(val imageUrls: List<String>) :
    ProductDetail(ProductDetailViewType.ImageCarousel)

class ProductDetailThumbnail(val imageUrls: List<String>) :
    ProductDetail(ProductDetailViewType.ThumbnailCarousel)

class ProductDetailSummary(
    val brandName: String,
    val name: String,
    val originalPrice: String,
    val discountedPrice: String,
    val saleText: String,
    val rating: Double
) : ProductDetail(ProductDetailViewType.Summary)

class ProductSeparator : ProductDetail(ProductDetailViewType.Separator)

class ProductIngredients(val ingredients: String) : ProductDetail(ProductDetailViewType.Ingredients)

class ProductDescription(val description: String) : ProductDetail(ProductDetailViewType.Description)

class ProductHowTo(val howTo: String) : ProductDetail(ProductDetailViewType.HowTo)

open class ProductDetail(val viewType: ProductDetailViewType)