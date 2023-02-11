package com.suhov.cryptocurrencuesviewer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.suhov.cryptocurrencuesviewer.R
import com.suhov.cryptocurrencuesviewer.room.CryptoDB
import com.suhov.cryptocurrencuesviewer.view.temp.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.asFlow
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.ext.android.viewModel

import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.sql.Time
import java.time.LocalDateTime
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timerTask


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CryptoDB.invoke(application)
    }


}