package com.loskon.cryptocoins.data

import com.loskon.cryptocoins.data.api.CoinGeckoApi
import com.loskon.cryptocoins.data.dto.CoinDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NetworkDataSource(
    private val coinGeckoApi: CoinGeckoApi
) {

    suspend fun getCoinsAsFlow(currency: String, pageSize: Int = 25): Flow<List<CoinDto>> {
        return flow {
            val response = coinGeckoApi.getCoins(currency, pageSize)

            if (response.isSuccessful) {
                emit(response.body() ?: emptyList())
            } else {
                emit(emptyList())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCoinAsFlow(id: String, localization: Boolean = false): Flow<CoinDto> {
        return flow {
            val response = coinGeckoApi.getCoin(id, localization)

            if (response.isSuccessful) {
                emit(response.body() ?: CoinDto())
            } else {
                emit(CoinDto())
            }
        }.flowOn(Dispatchers.IO)
    }
}