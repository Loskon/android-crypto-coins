package com.loskon.cryptocoins.data.dto

import com.loskon.cryptocoins.domain.CoinDescriptionModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinDescriptionDto(
    @Json(name = "en") val name: String? = null
)

fun CoinDescriptionDto.toCoinDescriptionModel(): CoinDescriptionModel {
    return CoinDescriptionModel(name = name ?: "")
}
