package com.suhov.cryptocurrencuesviewer.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.suhov.cryptocurrencuesviewer.modules.DataCryptoMutable
import com.suhov.cryptocurrencuesviewer.repository.DetailViewRepository

class DetailViewViewModel(private val repository: DetailViewRepository,
                          dataCryptoMutable: DataCryptoMutable) : ViewModel() {
    val showProgress = dataCryptoMutable.cryptoProgress
    val cryptoList = dataCryptoMutable.cryptoList
    val position = dataCryptoMutable.cryptoPosition
    val graph = dataCryptoMutable.cryptoGraph

    fun getStorageData(){
        repository.getDataFromDB()
    }

    fun changePosition(newPosition: Int) {
       repository.changePositionValue(newPosition)
       Log.d("getPositionData", "old position: ${position.value}")
       Log.d("getPositionData", "changePosition: $newPosition")


    }
    fun changeGraph(newPositionGraph: Int) {
        repository.changeGraph(newPositionGraph)
    }
    fun getDetailViewItem() {
        Log.d("getPositionData", "setObserve: position in ViewModel - ${position.value}")
        repository.getDetailView()
    }
}