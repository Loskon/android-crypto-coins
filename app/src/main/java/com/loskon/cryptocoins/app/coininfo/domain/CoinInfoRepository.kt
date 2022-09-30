package com.loskon.cryptocoins.app.coininfo.domain

import com.loskon.cryptocoins.domain.CoinModel
import kotlinx.coroutines.flow.Flow

interface CoinInfoRepository {
    suspend fun getCoinAsFlow(id: String): Flow<CoinModel>
}