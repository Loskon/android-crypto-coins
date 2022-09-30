package com.loskon.cryptocoins.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinDto(
    @Json(name = "id") val id: String? = null,
    @Json(name = "symbol") val symbol: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "image") val imageUrl: String? = null,
    @Json(name = "current_price") val currentPrice: Double? = null,
    @Json(name = "price_change_percentage_24h") val priceChangePercentage: Double? = null
)