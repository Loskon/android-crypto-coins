package com.loskon.cryptocoins.domain

data class CoinModel(
    val id: String = "",
    val symbol: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val currentPrice: Double = 0.0,
    val priceChangePercentage: Double = 0.0,
    val categories: List<String> = emptyList(),
    val description: String = ""
)