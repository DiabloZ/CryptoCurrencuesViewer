package com.suhov.cryptocurrencuesviewer.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import com.suhov.cryptocurrencuesviewer.network.models.list.CoinList
import com.suhov.cryptocurrencuesviewer.utils.Constants.Companion.API_KEY
import com.suhov.cryptocurrencuesviewer.utils.Constants.Companion.API_HEADER

interface ListCryptoNetwork {
    @Headers(("$API_HEADER:$API_KEY"))
    @GET("map")
    fun getListFromAPI():Call<CoinList>
}