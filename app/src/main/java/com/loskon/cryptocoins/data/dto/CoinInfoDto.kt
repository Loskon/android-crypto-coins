package com.loskon.cryptocoins.data.dto

import com.loskon.cryptocoins.domain.CoinDescriptionModel
import com.loskon.cryptocoins.domain.CoinImageModel
import com.loskon.cryptocoins.domain.CoinInfoModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinInfoDto(
    @Json(name = "id") val id: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "image") val imageUrls: CoinImageDto? = null,
    @Json(name = "categories") val categories: List<String>? = null,
    @Json(name = "description") val description: CoinDescriptionDto? = null
)

fun CoinInfoDto.toCoinInfoModel(): CoinInfoModel {
    return CoinInfoModel(
        id = id ?: "",
        name = name ?: "",
        imageUrls = imageUrls?.toCoinImageModel() ?: CoinImageModel(),
        categories = categories ?: emptyList(),
        description = description?.toCoinDescriptionModel() ?: CoinDescriptionModel()
    )
}