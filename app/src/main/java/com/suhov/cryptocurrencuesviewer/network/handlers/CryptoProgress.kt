package com.suhov.cryptocurrencuesviewer.network.handlers

import androidx.lifecycle.MutableLiveData
import com.suhov.cryptocurrencuesviewer.modules.DataCryptoMutable
import com.suhov.cryptocurrencuesviewer.network.CryptoProgress
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CryptoProgress(dataCryptoMutable: DataCryptoMutable) : CryptoProgress {
    private val showProgress = dataCryptoMutable.cryptoProgress
    override fun changeStateProgress() {
        CoroutineScope(Dispatchers.Main).launch { showProgress.value = !(showProgress.value != null && showProgress.value!!) }
    }
}