package com.suhov.cryptocurrencuesviewer.repository

import com.suhov.cryptocurrencuesviewer.network.handlers.CryptoFilter
import com.suhov.cryptocurrencuesviewer.network.handlers.CryptoNetwork

class ListCryptoRepository(private val cryFilter: CryptoFilter, private val cryNetwork: CryptoNetwork) {

    fun setFilter(find: String) {
        cryFilter.setFilter(find)
    }

    fun sendFilteredList() {
        cryFilter.sendFilteredList()
    }

    fun getCryptoList(){
        cryNetwork.getCryptoList()
    }

    fun getDataOfList() {
        cryNetwork.getDataOfList()
    }

    fun getImgOfList() {
        cryNetwork.getImgOfList()
    }

    fun forceUpdate() {
        cryNetwork.getDataOfList()
    }
}



