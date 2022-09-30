package com.loskon.cryptocoins.app.coininfo.presentation

import com.loskon.cryptocoins.app.coininfo.domain.CoinInfoInteractor
import com.loskon.cryptocoins.base.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

class CoinInfoViewModel(
    private val coinInfoInteractor: CoinInfoInteractor
) : BaseViewModel() {

    private val coinInfoMutableFlow = MutableStateFlow<CoinInfoState>(CoinInfoState.Loading)
    val coinInfoFlow get() = coinInfoMutableFlow.asStateFlow()

    private var job: Job? = null

    fun performCoinRequest(id: String) {
        job?.cancel()
        job = launchIOJob() {
            coinInfoInteractor.getCoinAsFlow(id)
                .onStart {
                    coinInfoMutableFlow.emit(CoinInfoState.Loading)
                }
                .catch {
                    coinInfoMutableFlow.emit(CoinInfoState.Error)
                }
                .collectLatest {
                    coinInfoMutableFlow.emit(CoinInfoState.Success(it))
                }
        }
    }
}