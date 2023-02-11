package com.suhov.cryptocurrencuesviewer.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suhov.cryptocurrencuesviewer.modules.DataCryptoMutable
import com.suhov.cryptocurrencuesviewer.network.models.CryptoData
import com.suhov.cryptocurrencuesviewer.repository.ListCryptoRepository

class ListCryptoViewModel(private val repository: ListCryptoRepository,
                          dataCryptoMutable: DataCryptoMutable): ViewModel() {

    val filterFind : MutableLiveData<String> = dataCryptoMutable.cryptoFilter
    val showProgress : MutableLiveData<Boolean> = dataCryptoMutable.cryptoProgress
    val filteredList : MutableLiveData<ArrayList<CryptoData>> = dataCryptoMutable.cryptoFilteredList
    val cryptoList : MutableLiveData<ArrayList<CryptoData>> = dataCryptoMutable.cryptoList

    fun getCryptoList() {repository.getCryptoList()}

    fun getDataOfList() {repository.getDataOfList()}

    fun getImgOfList() {repository.getImgOfList()}

    fun setFilter(find:String){repository.setFilter(find)}

    fun setUpListWithFilter(){repository.sendFilteredList()}

    fun updateList() {repository.forceUpdate()}
}