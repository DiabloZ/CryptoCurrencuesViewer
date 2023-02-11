package com.suhov.cryptocurrencuesviewer.repository

import android.util.Log
import com.suhov.cryptocurrencuesviewer.network.handlers.CryptoDataBase
import com.suhov.cryptocurrencuesviewer.network.handlers.CryptoNetwork
import com.suhov.cryptocurrencuesviewer.network.handlers.CryptoPosition


class DetailViewRepository(private val cryptoPosition: CryptoPosition,
                           private val cryptoDataBase: CryptoDataBase,
                           private val cryptoNetwork: CryptoNetwork){

    fun getDataFromDB(){
        cryptoDataBase.getDataFromDB()
    }

    fun changePositionValue(position: Int) {
        Log.d("getPositionData", "changePositionValue: $position")
        cryptoPosition.changePositionValue(position)
    }

    fun changeGraph(position: Int) {
        cryptoPosition.changeGraph(position)
    }

    fun getDetailView() {
        cryptoNetwork.getDetailView()
    }
}

