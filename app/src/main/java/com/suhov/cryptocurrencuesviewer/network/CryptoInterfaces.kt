package com.suhov.cryptocurrencuesviewer.network

import com.suhov.cryptocurrencuesviewer.network.models.CryptoData
import com.suhov.cryptocurrencuesviewer.network.models.current.DataItem
import com.suhov.cryptocurrencuesviewer.network.models.current.GraphList
import retrofit2.Call

//ListView
interface ListNetwork {
    fun getCryptoList()
    fun getDataOfList()
    fun getImgOfList()
    fun forceUpdate()
}

interface CryptoFilter {
    fun setFilter(find: String)
    fun sendFilteredList()
}

interface CryptoProgress {
    fun changeStateProgress()
}

//DetailView
interface CryptoPosition {
    fun changePositionValue(position: Int)
    fun changeGraph(position: Int)
}

interface DetailNetwork {
    fun getDetailView()
    fun getRetroInstance(): Call<GraphList>
    fun needData(): Boolean
}
interface CryptoConverter {
    fun convertDataToArrays(data: List<DataItem>, cryptoData: CryptoData): CryptoData
    fun convertData(data: List<DataItem>, numberDay: Int): List<DataItem>
}
