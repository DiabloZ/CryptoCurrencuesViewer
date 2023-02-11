package com.suhov.cryptocurrencuesviewer.room

import android.app.Application
import com.suhov.cryptocurrencuesviewer.network.models.CryptoData

interface CryptoDataBase: CryptoDataBaseInsert, CryptoDataBaseGet, CryptoDataBaseUpdate

interface CryptoDataBaseInsert {
    fun insertToDB (list: List<CryptoData>)
}
interface CryptoDataBaseGet {
    fun getDataFromDB()
}
interface CryptoDataBaseUpdate{
    fun updateCryptoItem(cryptoData: CryptoData)
}