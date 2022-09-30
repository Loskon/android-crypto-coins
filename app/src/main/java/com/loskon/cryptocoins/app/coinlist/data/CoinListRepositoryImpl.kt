package com.loskon.cryptocoins.app.coinlist.data

import com.loskon.cryptocoins.app.coinlist.domain.CoinListRepository
import com.loskon.cryptocoins.data.NetworkDataSource
import com.loskon.cryptocoins.data.dto.toCoinModel
import com.loskon.cryptocoins.domain.CoinModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoinListRepositoryImpl(
    private val networkDataSource: NetworkDataSource
) : CoinListRepository {

    override suspend fun getCoinsAsFlow(currency: String): Flow<List<CoinModel>> {
        return networkDataSource.getCoinsAsFlow(currency).map { coinList ->
            coinList.map { it.toCoinModel() }
        }
    }
}