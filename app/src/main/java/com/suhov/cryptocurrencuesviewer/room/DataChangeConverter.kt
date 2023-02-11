package com.suhov.cryptocurrencuesviewer.room

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.suhov.cryptocurrencuesviewer.network.models.current.DataItem

import java.util.stream.Collectors.joining
import kotlin.collections.ArrayList


class DataChangeConverter{
    @RequiresApi(Build.VERSION_CODES.N)
    @TypeConverter
    fun fromChangeData(dataChange: List<DataItem>) : String{
        var tempString = ""
        for (i in dataChange.indices) {
            tempString += "${dataChange[i].close.toString()},${dataChange[i].timeConvert}"
            if (i != dataChange.size-1) tempString += ";"
        }
        return tempString
    }

    @TypeConverter
    fun toChangeData(data: String) : List<DataItem>{
        val tempArr = listOf(data.split(";"))
        val tempList:MutableList<DataItem> = ArrayList()
            if (data != ""){
                    for (arr in tempArr[0]){
                        val new = DataItem()
                        val tempString = arr.split(",")
                        new.close = tempString[0].toDouble()
                        new.timeConvert = tempString[1]
                        tempList.add(new)
                    }
            }
        return tempList
    }
}