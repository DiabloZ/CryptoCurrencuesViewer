package com.suhov.cryptocurrencuesviewer.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.suhov.cryptocurrencuesviewer.network.models.current.GraphList

interface DetailsViewNetwork {
    @GET("histohour")
    fun getCurrentCryptoDataDay(
            @Query("fsym") coinSymbol: String,
            @Query("tsym") currency:String,
            @Query("aggregate") scope:String,
            @Query("limit") dayInterval:String
    ): Call<GraphList>

    @GET("histoday")
    fun getCurrentCryptoDataOther(
            @Query("fsym") coinSymbol: String,
            @Query("tsym") currency:String,
            @Query("aggregate") scope:String,
            @Query("limit") dayInterval:String
    ): Call<GraphList>
}