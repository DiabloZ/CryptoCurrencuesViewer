package com.suhov.cryptocurrencuesviewer.network

import com.suhov.cryptocurrencuesviewer.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListCryptoInstance {
    companion object {
        fun getRetrofitInstanceCryptoList(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(Constants.CRYPTO_LIST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}