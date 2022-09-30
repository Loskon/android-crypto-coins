package com.loskon.cryptocoins.data

import com.loskon.cryptocoins.BuildConfig
import com.loskon.cryptocoins.data.api.CoinGeckoApi
import com.loskon.cryptocoins.data.moshiadapter.CoinGeckoMoshiAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideLoggingInterceptor() }
    single { provideOkHttp(get()) }
    single { provideMoshi() }
    single { provideRetrofit(get(), get()) }
    single { provideCoinGeckoApi(get()) }

    single { NetworkDataSource(get()) }
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

private fun provideOkHttp(logging: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) addInterceptor(logging)
    }.build()
}

private fun provideMoshi(): Moshi {
    return Moshi.Builder().add(CoinGeckoMoshiAdapter()).build()
}

private fun provideRetrofit(okHttp: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .client(okHttp)
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}

private fun provideCoinGeckoApi(retrofit: Retrofit): CoinGeckoApi {
    return retrofit.create(CoinGeckoApi::class.java)
}