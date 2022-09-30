package com.loskon.cryptocoins.app.coinlist.presentation

import com.loskon.cryptocoins.domain.CoinModel

sealed class CoinListState {
    object Loading : CoinListState()
    data class Success(val coins: List<CoinModel>) : CoinListState()
    object Failure : CoinListState()
}