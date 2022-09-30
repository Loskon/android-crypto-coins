package com.loskon.cryptocoins.app.coinlist.presentation

import com.loskon.cryptocoins.app.coinlist.domain.CoinListInteractor
import com.loskon.cryptocoins.base.presentation.BaseViewModel
import com.loskon.cryptocoins.domain.CoinModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

class CoinListViewModel(
    private val coinListInteractor: CoinListInteractor
) : BaseViewModel() {

    private val coinListMutableStateFlow = MutableStateFlow<List<CoinModel>>(emptyList())
    val coinListStateFlow get() = coinListMutableStateFlow.asStateFlow()

    private var job: Job? = null

    fun performCoinsRequest(currency: String) {
        job?.cancel()
        job = launchIOJob() {
            coinListInteractor.getCoinsAsFlow(currency).collectLatest { coinListMutableStateFlow.emit(it) }
        }
    }
}