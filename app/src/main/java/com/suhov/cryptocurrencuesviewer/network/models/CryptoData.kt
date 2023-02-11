package com.suhov.cryptocurrencuesviewer.network.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.suhov.cryptocurrencuesviewer.network.models.current.DataItem
import com.suhov.cryptocurrencuesviewer.room.DataChangeConverter

@Entity(tableName = "crypto_database")
@TypeConverters(DataChangeConverter::class)
data class CryptoData(
                 @field:SerializedName("id")
                 @PrimaryKey var id:Int = 0,
                 @field:SerializedName("name")
                 var name:String = "",
                 @field:SerializedName("symbol")
                 var symbol:String = "",
                 var imgURL:String = "",
                 var last_updated:String = "",
                 var price:Double = 0.0,
                 var percent_change_1h:Double = 0.0,
                 var percent_change_24h:Double = 0.0,
                 var percent_change_7d:Double = 0.0,
                 var data_change_day:List<DataItem> = ArrayList(),
                 var data_change_week:List<DataItem> = ArrayList(),
                 var data_change_month:List<DataItem> = ArrayList(),
                 var data_change_three_month:List<DataItem> = ArrayList(),
                 var data_change_six_month:List<DataItem> = ArrayList(),
                 var data_change_year:List<DataItem> = ArrayList(),
                 var data_change_allTime:List<DataItem> = ArrayList())

