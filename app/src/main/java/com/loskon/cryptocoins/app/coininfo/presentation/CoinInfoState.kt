package com.loskon.cryptocoins.app.coininfo.presentation

import com.loskon.cryptocoins.domain.CoinModel

sealed class CoinInfoState {
    object Loading : CoinInfoState()
    data class Success(val coin: CoinModel) : CoinInfoState()
    object Error : CoinInfoState()
}