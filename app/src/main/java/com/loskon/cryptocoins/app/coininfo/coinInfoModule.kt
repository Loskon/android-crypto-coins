package com.loskon.cryptocoins.app.coininfo

import com.loskon.cryptocoins.app.coininfo.data.CoinInfoRepositoryImpl
import com.loskon.cryptocoins.app.coininfo.domain.CoinInfoInteractor
import com.loskon.cryptocoins.app.coininfo.domain.CoinInfoRepository
import com.loskon.cryptocoins.app.coininfo.presentation.CoinInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coinInfoModule = module {
    single<CoinInfoRepository> { CoinInfoRepositoryImpl(get()) }
    factory { CoinInfoInteractor(get()) }
    viewModel { CoinInfoViewModel(get()) }
}