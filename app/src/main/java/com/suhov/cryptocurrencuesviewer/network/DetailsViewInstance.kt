package com.suhov.cryptocurrencuesviewer.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.suhov.cryptocurrencuesviewer.utils.Constants

class DetailsViewInstance {
    companion object {
        fun getDetailViewItem(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(Constants.GRAPH_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}