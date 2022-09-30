package com.loskon.cryptocoins.app.coinlist.domain

import com.loskon.cryptocoins.domain.CoinModel
import kotlinx.coroutines.flow.Flow

interface CoinListRepository {
    suspend fun getCoinsAsFlow(currency: String): Flow<List<CoinModel>>
}