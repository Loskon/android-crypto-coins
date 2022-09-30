package com.loskon.cryptocoins.app.coininfo.domain

import com.loskon.cryptocoins.domain.CoinModel
import kotlinx.coroutines.flow.Flow

class CoinInfoInteractor(
    private val coinInfoRepository: CoinInfoRepository
) {

    suspend fun getCoinAsFlow(id: String): Flow<CoinModel> {
        return coinInfoRepository.getCoinAsFlow(id)
    }
}