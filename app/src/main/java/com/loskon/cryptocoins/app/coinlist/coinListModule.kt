package com.loskon.cryptocoins.app.coinlist

import com.loskon.cryptocoins.app.coinlist.data.CoinListRepositoryImpl
import com.loskon.cryptocoins.app.coinlist.domain.CoinListInteractor
import com.loskon.cryptocoins.app.coinlist.domain.CoinListRepository
import com.loskon.cryptocoins.app.coinlist.presentation.CoinListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coinListModule = module {
    single<CoinListRepository> { CoinListRepositoryImpl(get()) }
    factory { CoinListInteractor(get()) }
    viewModel { CoinListViewModel(get()) }
}