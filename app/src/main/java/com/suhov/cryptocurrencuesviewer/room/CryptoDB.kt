package com.suhov.cryptocurrencuesviewer.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.suhov.cryptocurrencuesviewer.network.models.CryptoData

@Database(version = 1, entities = [CryptoData::class])
abstract class CryptoDB : RoomDatabase() {
    companion object{
        @Volatile private var instance: CryptoDB? = null
        private var LOCK = Any()

        operator fun invoke(application: Application) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(application).also{ instance = it}
        }

        private fun buildDatabase(application: Application) =
            Room.databaseBuilder(application, CryptoDB::class.java, "crypto_database.db")
                .build()
    }

    abstract fun getCryptoDao(): CryptoDBDao
}
