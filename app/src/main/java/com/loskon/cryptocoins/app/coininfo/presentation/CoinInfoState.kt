package com.loskon.cryptocoins.app.coininfo.presentation

import com.loskon.cryptocoins.domain.CoinInfoModel

sealed class CoinInfoState {
    object Loading : CoinInfoState()
    data class Success(val coin: CoinInfoModel) : CoinInfoState()
    object Error : CoinInfoState()
}