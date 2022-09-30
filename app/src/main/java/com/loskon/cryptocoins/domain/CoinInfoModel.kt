package com.loskon.cryptocoins.domain

data class CoinInfoModel(
    val id: String = "",
    val name: String = "",
    val imageUrl: List<CoinImageModel> = emptyList(),
    val categories: List<String> = emptyList(),
    val description: List<CoinDescriptionModel> = emptyList(),
)
