package com.suhov.cryptocurrencuesviewer.network.handlers

import android.util.Log
import com.suhov.cryptocurrencuesviewer.modules.DataCryptoMutable
import com.suhov.cryptocurrencuesviewer.network.CryptoFilter

class CryptoFilter(dataCryptoMutable: DataCryptoMutable):CryptoFilter {
    private val filterFind = dataCryptoMutable.cryptoFilter
    private val cryptoList = dataCryptoMutable.cryptoList
    private val filteredList = dataCryptoMutable.cryptoFilteredList

    override fun setFilter(find: String) {
        filterFind.value = find
    }

    override fun sendFilteredList() {
        Log.d("getPositionData", "CryptoFilter - sendFilteredList() - ${filterFind.value}")
        if (filterFind.value != null) {
            val filtered = (cryptoList.value!!.filter { it.name.toLowerCase().contains(filterFind.value!!.toLowerCase())}).toMutableList()
            val filtered2 = cryptoList.value!!.filter { it.symbol.toLowerCase().contains(filterFind.value!!.toLowerCase())}
            for (item in filtered2) {
                if (!filtered.contains(item)) filtered.add(item)
            }
            filtered.sortBy {it.id}
            filteredList.value = ArrayList(filtered)
        }
    }
}