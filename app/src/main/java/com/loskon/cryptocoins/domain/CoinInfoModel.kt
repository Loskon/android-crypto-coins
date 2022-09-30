package com.loskon.cryptocoins.domain

data class CoinInfoModel(
    val id: String = "",
    val name: String = "",
    val imageUrls: CoinImageModel = CoinImageModel(),
    val categories: List<String> = emptyList(),
    val description: CoinDescriptionModel = CoinDescriptionModel(),
)
