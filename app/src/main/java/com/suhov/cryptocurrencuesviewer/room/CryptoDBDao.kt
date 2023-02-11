package com.suhov.cryptocurrencuesviewer.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.suhov.cryptocurrencuesviewer.network.models.CryptoData

@Dao
interface CryptoDBDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCryptoDataList(list: List<CryptoData>)

    @Update
    fun updateCryptoData(cryptoData: CryptoData)

    @Query("SELECT * FROM crypto_database where id like :search")
    fun getCryptoData(search:String) : LiveData<List<CryptoData>>

    @Query("SELECT * FROM crypto_database")
    fun getAllData() : List<CryptoData>
}