package com.loskon.cryptocoins.app.coininfo.data

import com.loskon.cryptocoins.app.coininfo.domain.CoinInfoRepository
import com.loskon.cryptocoins.data.NetworkDataSource
import com.loskon.cryptocoins.data.dto.toCoinModel
import com.loskon.cryptocoins.domain.CoinModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoinInfoRepositoryImpl(
    private val networkDataSource: NetworkDataSource
) : CoinInfoRepository {

    override suspend fun getCoinAsFlow(id: String): Flow<CoinModel> {
        return networkDataSource.getCoinAsFlow(id).map { it.toCoinModel() }
    }
}