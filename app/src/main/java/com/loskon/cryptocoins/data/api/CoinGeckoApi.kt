package com.loskon.cryptocoins.data.api

import com.loskon.cryptocoins.data.dto.CoinDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {

    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String,
        @Query("per_page") perPage: Int
    ): Response<List<CoinDto>>

    @GET("coins/{id}")
    suspend fun getCoin(
        @Path("id") id: String,
        @Query("localization") localization: Boolean
    ): Response<CoinDto>
}