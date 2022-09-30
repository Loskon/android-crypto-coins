package com.loskon.cryptocoins.data.dto

import com.loskon.cryptocoins.domain.CoinImageModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinImageDto(
    @Json(name = "large") val name: String? = null
)

fun CoinImageDto.toCoinImageModel(): CoinImageModel {
    return CoinImageModel(name = name ?: "")
}
