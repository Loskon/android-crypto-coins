package com.loskon.cryptocoins.app.coininfo.presentation

import com.loskon.cryptocoins.app.coininfo.domain.CoinInfoInteractor
import com.loskon.cryptocoins.base.presentation.BaseViewModel
import com.loskon.cryptocoins.domain.CoinInfoModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

class CoinInfoViewModel(
    private val coinInfoInteractor: CoinInfoInteractor
) : BaseViewModel() {

    private val coinInfoMutableFlow = MutableStateFlow(CoinInfoModel())
    val coinInfoFlow get() = coinInfoMutableFlow.asStateFlow()

    private var job: Job? = null

    fun performCoinRequest(id: String) {
        job?.cancel()
        job = launchIOJob() {
            coinInfoInteractor.getCoinAsFlow(id).collectLatest { coinInfoMutableFlow.emit(it) }
        }
    }
}