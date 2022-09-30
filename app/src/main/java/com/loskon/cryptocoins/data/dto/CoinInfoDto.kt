package com.loskon.cryptocoins.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinInfoDto(
    @Json(name = "id") val id: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "image") val imageUrl: List<CoinImageDto>? = null,
    @Json(name = "categories") val categories: List<String>? = null,
    @Json(name = "description") val description: List<String>? = null
)