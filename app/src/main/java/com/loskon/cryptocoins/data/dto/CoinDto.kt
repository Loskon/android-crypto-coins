package com.loskon.cryptocoins.data.dto

import com.loskon.cryptocoins.domain.CoinModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinDto(
    @Json(name = "id") val id: String? = null,
    @Json(name = "symbol") val symbol: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "image") val imageUrl: String? = null,
    @Json(name = "current_price") val currentPrice: Double? = null,
    @Json(name = "price_change_percentage_24h") val priceChangePercentage: Double? = null,
    @Json(name = "categories") val categories: List<String>? = null,
    @Json(name = "description") val description: String? = null
)

fun CoinDto.toCoinModel(): CoinModel {
    return CoinModel(
        id = id ?: "",
        symbol = symbol ?: "",
        name = name ?: "",
        imageUrl = imageUrl ?: "",
        currentPrice = currentPrice ?: 0.0,
        priceChangePercentage = priceChangePercentage ?: 0.0,
        categories = categories ?: emptyList(),
        description = description ?: ""
    )
}
