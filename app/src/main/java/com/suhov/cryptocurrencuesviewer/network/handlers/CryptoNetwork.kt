package com.suhov.cryptocurrencuesviewer.network.handlers

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import com.suhov.cryptocurrencuesviewer.modules.DataCryptoMutable
import com.suhov.cryptocurrencuesviewer.network.*
import com.suhov.cryptocurrencuesviewer.network.CryptoFilter
import com.suhov.cryptocurrencuesviewer.network.CryptoProgress
import com.suhov.cryptocurrencuesviewer.network.models.CryptoData
import com.suhov.cryptocurrencuesviewer.network.models.current.DataItem
import com.suhov.cryptocurrencuesviewer.network.models.current.GraphList
import com.suhov.cryptocurrencuesviewer.network.models.list.CoinList
import com.suhov.cryptocurrencuesviewer.room.CryptoDB
import com.suhov.cryptocurrencuesviewer.room.CryptoDataBase
import com.suhov.cryptocurrencuesviewer.room.CryptoDataBaseGet
import com.suhov.cryptocurrencuesviewer.room.CryptoDataBaseInsert
import com.suhov.cryptocurrencuesviewer.utils.Constants
import com.suhov.cryptocurrencuesviewer.utils.DateHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.measureTimeMillis

private const val dataJsonList = "data"
private const val quoteJsonList = "quote"
private const val currenciesJsonList = "USD"
private const val priceJsonList = "price"
private const val changeHourJsonList = "percent_change_1h"
private const val changeDayJsonList = "percent_change_24h"
private const val changeWeekJsonList = "percent_change_7d"
private const val dataJsonImg = "Data"
private const val imageURLJsonImg = "ImageUrl"
private const val lastUpdate = "last_updated"
private const val safeQuery = "1"
private const val loadLimit = 10

class CryptoNetwork(dataCryptoMutable: DataCryptoMutable,
                    private val cryptoProgress: CryptoProgress,
                    private val cryptoDB:CryptoDataBase,
                    private val cryptoFilter:CryptoFilter,
                    private val cryptoConverter:CryptoConverter) : ListNetwork, DetailNetwork {
    private var cryptoList = dataCryptoMutable.cryptoList
    private var positionCrypto = dataCryptoMutable.cryptoPosition
    private var graphCrypto = dataCryptoMutable.cryptoGraph
    private val dayGraphValue = 0
    private val defaultCurrencies = "USD"
    private val scopeRequest = "1"
    private val dayInterval = "24"
    private val maxInterval = "2000"
    private val retroInstance = DetailsViewInstance
            .getDetailViewItem()
            .create(DetailsViewNetwork::class.java)

    override fun getCryptoList(){
        CoroutineScope(Dispatchers.IO).launch {
            if (cryptoList.value.isNullOrEmpty()) {
                cryptoProgress.changeStateProgress()
                val retroInstance = ListCryptoInstance
                        .getRetrofitInstanceCryptoList()
                        .create(ListCryptoNetwork::class.java)
                val request = retroInstance.getListFromAPI()

                request.enqueue(object : Callback<CoinList> {
                    override fun onResponse(call: Call<CoinList>, response: Response<CoinList>) {
                        val dataList = response.body()?.data
                        cryptoList.value = dataList as ArrayList<CryptoData>?
                        cryptoProgress.changeStateProgress()
                    }

                    override fun onFailure(call: Call<CoinList>, t: Throwable) {
                        Log.e(TAG, "onFailure: ", t)
                        cryptoProgress.changeStateProgress()
                    }
                })
            }
        }
    }

    @SuppressLint("NewApi")
    override fun getDataOfList() {
        cryptoProgress.changeStateProgress()
        CoroutineScope(Dispatchers.IO).launch {
            val executionTime = measureTimeMillis {
                Log.i(TAG, "onResponse: getDataOfList go to internet!")
                val tempCryptoList = cryptoList

                var query = ""
                var counter = 0
                for (string in cryptoList.value!!) {
                    query += string.id.toString() + ","
                    counter++
                    if (counter == loadLimit) break
                }
                query += safeQuery

                val request = Request.Builder()
                        .url(Constants.CRYPTO_DATA_URL + query)
                        .header(Constants.API_HEADER, Constants.API_KEY)
                        .build()

                val client = OkHttpClient()

                val response:okhttp3.Response = client.newCall(request).execute()

                val jsonResponse = response.body()?.string() as String
                val jsonObject = JSONObject(jsonResponse)
                val jsonData = jsonObject.getJSONObject(dataJsonList)

                for (entry in tempCryptoList.value!!) {
                    if (jsonData.has(entry.id.toString()) && !jsonData.isNull(entry.id.toString())) {
                        val jsonCryptoItem = jsonData.getJSONObject(entry.id.toString())
                        val jsonQuote = jsonCryptoItem.getJSONObject(quoteJsonList)
                        val jsonUSD = jsonQuote.getJSONObject(currenciesJsonList)

                        entry.price = jsonUSD.getDouble(priceJsonList)
                        entry.percent_change_1h = jsonUSD.getDouble(changeHourJsonList)
                        entry.percent_change_24h = jsonUSD.getDouble(changeDayJsonList)
                        entry.percent_change_7d = jsonUSD.getDouble(changeWeekJsonList)

                        entry.last_updated = DateHandler().getNewDataFormatFromString(jsonUSD.getString(lastUpdate))
                    }
                }
                withContext(Dispatchers.Main){
                    cryptoList.value = tempCryptoList.value
                    cryptoFilter.sendFilteredList()
                    cryptoProgress.changeStateProgress()
                }
            }
            Log.i(TAG, "onResponse: getDataOfList done - $executionTime!")
        }
    }

    override fun getImgOfList() {
        CoroutineScope(Dispatchers.IO).launch {
            val executionTime = measureTimeMillis {
                Log.i(TAG, "onResponse: getImgOfList go to internet!")
                val tempCryptoList = cryptoList

                val request = Request.Builder()
                        .url(Constants.IMG_URL)
                        .build()

                val client = OkHttpClient()

                val response = client.newCall(request).execute()

                val jsonResponse = response.body()?.string()
                val jsonObject = JSONObject(jsonResponse)
                val jsonData = jsonObject.getJSONObject(dataJsonImg)

                for (entry in tempCryptoList.value!!) {
                    if (jsonData.has(entry.symbol) && !jsonData.isNull(entry.symbol)) {
                        val jsonCryptoItem = jsonData.getJSONObject(entry.symbol)
                        if (jsonCryptoItem.has(imageURLJsonImg) && !jsonCryptoItem.isNull(imageURLJsonImg)) {
                            entry.imgURL = jsonCryptoItem.getString(imageURLJsonImg)
                        }
                    }
                }
                cryptoDB.insertToDB(tempCryptoList.value!!)
                withContext(Dispatchers.Main){cryptoList.value = tempCryptoList.value
                }
            }
            Log.i(TAG, "onResponse: getImgOfList done - $executionTime!")

        }
    }

    override fun forceUpdate() {
        getDataOfList()
    }

    override fun getDetailView() {
        val tempCryptoList = cryptoList
        cryptoProgress.changeStateProgress()

        val request = getRetroInstance()
        //TODO delay
        if (needData()) {
            request.enqueue(object : Callback<GraphList> {
                override fun onResponse(call: Call<GraphList>, response: Response<GraphList>) {
                    Log.i(TAG, "onResponse: go network!")
                    val data: List<DataItem> = response.body()?.data as List<DataItem>
                    for (dataItem in data) {
                        dataItem.timeConvert =
                                DateHandler().getNewDataFormatFromInt(dataItem.time!!)
                    }
                    tempCryptoList.value!![positionCrypto.value!!] =
                            cryptoConverter.convertDataToArrays(data, tempCryptoList.value!![positionCrypto.value!!])
                    cryptoList = tempCryptoList
                    CoroutineScope(Dispatchers.IO).launch {
                        cryptoDB.updateCryptoItem(tempCryptoList.value!![positionCrypto.value!!])
                    }
                    cryptoProgress.changeStateProgress()
                }

                override fun onFailure(call: Call<GraphList>, t: Throwable) {
                    Log.e(TAG, "onFailure: ", t)
                    cryptoProgress.changeStateProgress()
                }
            })
        }else{cryptoProgress.changeStateProgress()}
    }

    override fun needData(): Boolean {
        if (graphCrypto.value == dayGraphValue) {
            if (!cryptoList.value!![positionCrypto.value!!].data_change_day.isNullOrEmpty()) return false
        } else {
            if (!cryptoList.value!![positionCrypto.value!!].data_change_year.isNullOrEmpty()) return false
        }
        return true
    }

    override fun getRetroInstance(): Call<GraphList> {
        return if (graphCrypto.value == dayGraphValue) {
            retroInstance.getCurrentCryptoDataDay(cryptoList.value!![positionCrypto.value!!].symbol, defaultCurrencies, scopeRequest, dayInterval)
        } else {
            retroInstance.getCurrentCryptoDataOther(cryptoList.value!![positionCrypto.value!!].symbol, defaultCurrencies, scopeRequest, maxInterval)
        }
    }
}