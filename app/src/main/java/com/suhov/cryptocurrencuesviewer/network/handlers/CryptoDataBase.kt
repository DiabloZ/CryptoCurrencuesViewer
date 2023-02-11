package com.suhov.cryptocurrencuesviewer.network.handlers

import android.app.Application
import android.util.Log
import com.suhov.cryptocurrencuesviewer.modules.DataCryptoMutable
import com.suhov.cryptocurrencuesviewer.network.models.CryptoData
import com.suhov.cryptocurrencuesviewer.room.CryptoDB
import com.suhov.cryptocurrencuesviewer.room.CryptoDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList
import kotlin.system.measureTimeMillis

class CryptoDataBase (val application: Application, dataCryptoMutable: DataCryptoMutable) : CryptoDataBase {
    private val cryptoList = dataCryptoMutable.cryptoList
    private val TAG = "CryptoDataBase"

    override fun insertToDB(list: List<CryptoData>) {
        CoroutineScope(Dispatchers.IO).launch {
            CryptoDB.invoke(application).getCryptoDao().insertCryptoDataList(list)
        }
    }

    override fun getDataFromDB() {
        CoroutineScope(Dispatchers.IO).launch {
            val timer = measureTimeMillis {
                val tempArr = ArrayList(CryptoDB.invoke(application).getCryptoDao().getAllData())
                withContext(Dispatchers.Main){
                    cryptoList.value = tempArr
                }
            }
            Log.i(TAG, "getDataFromDB: done - $timer")
        }
    }

    override fun updateCryptoItem(cryptoData: CryptoData) {
        CoroutineScope(Dispatchers.IO).launch {
            CryptoDB.invoke(application).getCryptoDao().updateCryptoData(cryptoData)
        }
    }
}