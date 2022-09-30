package com.loskon.cryptocoins.app.coinlist.presentation

import com.loskon.cryptocoins.app.coinlist.domain.CoinListInteractor
import com.loskon.cryptocoins.base.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

class CoinListViewModel(
    private val coinListInteractor: CoinListInteractor
) : BaseViewModel() {

    private val coinListMutableStateFlow = MutableStateFlow<CoinListState>(CoinListState.Loading)
    val coinListStateFlow get() = coinListMutableStateFlow.asStateFlow()

    private var job: Job? = null

    fun performCoinsRequest(currency: String) {
        job?.cancel()
        job = launchIOJob() {
            coinListInteractor.getCoinsAsFlow(currency)
                .onStart {
                    coinListMutableStateFlow.emit(CoinListState.Loading)
                }
                .catch {
                    coinListMutableStateFlow.emit(CoinListState.Failure)
                }
                .collectLatest {
                    coinListMutableStateFlow.emit(CoinListState.Success(it))
                }
        }
    }
}