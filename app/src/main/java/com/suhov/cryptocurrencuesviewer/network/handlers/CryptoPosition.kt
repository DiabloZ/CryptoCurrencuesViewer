package com.suhov.cryptocurrencuesviewer.network.handlers

import android.util.Log
import com.suhov.cryptocurrencuesviewer.modules.DataCryptoMutable
import com.suhov.cryptocurrencuesviewer.network.CryptoPosition

class CryptoPosition(dataCryptoMutable: DataCryptoMutable): CryptoPosition {
    private var cryptoPosition = dataCryptoMutable.cryptoPosition
    private var cryptoGraph = dataCryptoMutable.cryptoGraph

    override fun changePositionValue(position: Int) {
        Log.d("getPositionData", "CryptoPosition changePositionValue: $position")
        cryptoPosition.value = position
    }

    override fun changeGraph(position: Int) {
        Log.d("getPositionData", "CryptoPosition changePositionValue: $position")
        cryptoGraph.value = position
    }


}