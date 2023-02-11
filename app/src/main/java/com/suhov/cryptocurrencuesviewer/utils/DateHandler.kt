package com.suhov.cryptocurrencuesviewer.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.TemporalAccessor
import java.util.*
import java.util.Date.from

class DateHandler {
    private val NEW_DATE_FORMAT = "HH:mm:ss  d.MM.yyyy"


    @RequiresApi(Build.VERSION_CODES.O)
    fun getNewDataFormatFromString(dateParse: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("HH:mm:ss  d.MM.yyyy")
        val output = formatter.format(parser.parse("2018-12-14T09:55:00"))

/*        val accessor:TemporalAccessor = LocalDateTime.parse(dateParse)
        val date: Date = from(Instant.from(accessor))*/
        return formatter.format(parser.parse(dateParse))
    }

    @SuppressLint("SimpleDateFormat")
    fun getNewDataFormatFromInt(dateParse: Int): String {
        return SimpleDateFormat(NEW_DATE_FORMAT).format(Date((dateParse * 1000).toLong()))
    }
}