package com.suhov.cryptocurrencuesviewer.network.models.list

import com.google.gson.annotations.SerializedName
import com.suhov.cryptocurrencuesviewer.network.models.CryptoData

data class CoinList(

	@field:SerializedName("data")
	val data: List<CryptoData?>? = null,

)

