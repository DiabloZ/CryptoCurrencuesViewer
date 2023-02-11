package com.suhov.cryptocurrencuesviewer.network.handlers

import com.suhov.cryptocurrencuesviewer.modules.DataCryptoMutable
import com.suhov.cryptocurrencuesviewer.network.CryptoConverter
import com.suhov.cryptocurrencuesviewer.network.models.CryptoData
import com.suhov.cryptocurrencuesviewer.network.models.current.DataItem

class CryptoDataConverter(dataCryptoMutable: DataCryptoMutable): CryptoConverter {
    private val day = 24
    private val week = 7
    private val month = 30
    private val threeMonth = 90
    private val sixMonth = 182
    private val year = 365
    private val dayGraphValue = 0
    private val graph = dataCryptoMutable.cryptoGraph

    override fun convertDataToArrays(data: List<DataItem>, cryptoData: CryptoData): CryptoData {
        if (graph.value == dayGraphValue && data.size > day) {
            cryptoData.data_change_day = data
        } else {
            cryptoData.data_change_allTime = data
            cryptoData.data_change_year = convertData(data, year)
            cryptoData.data_change_six_month = convertData(data, sixMonth)
            cryptoData.data_change_three_month = convertData(data, threeMonth)
            cryptoData.data_change_month = convertData(data, month)
            cryptoData.data_change_week = convertData(data, week)
        }
        return cryptoData
    }

    override fun convertData(data: List<DataItem>, numberDay: Int): List<DataItem> {
        val tempData = arrayListOf<DataItem>()
        for (i in numberDay downTo 0 ) {
            if (data.reversed().size > i) {
                tempData.add(data.reversed()[i])
            }
        }
        return tempData
    }
}