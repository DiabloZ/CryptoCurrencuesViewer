package com.suhov.cryptocurrencuesviewer.modules

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.suhov.cryptocurrencuesviewer.adapters.DetailViewAdapter
import com.suhov.cryptocurrencuesviewer.adapters.ListCryptoRecyclerAdapter
import com.suhov.cryptocurrencuesviewer.network.handlers.*
import com.suhov.cryptocurrencuesviewer.network.models.CryptoData
import com.suhov.cryptocurrencuesviewer.repository.DetailViewRepository
import com.suhov.cryptocurrencuesviewer.repository.ListCryptoRepository
import com.suhov.cryptocurrencuesviewer.viewModel.DetailViewViewModel
import com.suhov.cryptocurrencuesviewer.viewModel.ListCryptoViewModel
import org.koin.dsl.module

val listViewModel = module {
    single {ListCryptoViewModel(
            get<ListCryptoRepository>(),
            get<DataCryptoMutable>())}
    single { CryptoDataBase(get<Application>(),
            get<DataCryptoMutable>()) }
    single { CryptoProgress(get<DataCryptoMutable>()) }
    single { CryptoFilter(get<DataCryptoMutable>()) }
    single { CryptoNetwork(get<DataCryptoMutable>(),
            get<CryptoProgress>(),
            get<CryptoDataBase>(),
            get<CryptoFilter>(),
            get<CryptoDataConverter>())}
}

val listAdapter = module {
    single {ListCryptoRecyclerAdapter(get<DataCryptoMutable>())}
}

val listRepository = module {
    single {ListCryptoRepository(
            get<CryptoFilter>(),
            get<CryptoNetwork>())}
}

val detailsViewModel = module {
    single {DetailViewViewModel(get<DetailViewRepository>(),
            get<DataCryptoMutable>())}
}
val detailsAdapter = module {
    single { DetailViewAdapter(get<DataCryptoMutable>())}
}
val detailRepository = module {
    single { CryptoPosition(get<DataCryptoMutable>())}
    single { CryptoDataConverter(get<DataCryptoMutable>())}
    single { DetailViewRepository(get<CryptoPosition>(),
            get<CryptoDataBase>(),
            get<CryptoNetwork>())}
}

val dataRepository = module {
    val cryptoList = MutableLiveData<ArrayList<CryptoData>>()
    val cryptoProgress = MutableLiveData<Boolean>()
    val cryptoFilteredList = MutableLiveData<ArrayList<CryptoData>>()
    val cryptoFilter = MutableLiveData<String>()
    val cryptoPosition = MutableLiveData<Int>()
    val cryptoGraph = MutableLiveData<Int>()

    single {DataCryptoMutable(
            cryptoList,
            cryptoProgress,
            cryptoFilteredList,
            cryptoFilter,
            cryptoPosition,
            cryptoGraph)}
}

data class DataCryptoMutable(val cryptoList: MutableLiveData<ArrayList<CryptoData>>,
                             val cryptoProgress: MutableLiveData<Boolean>,
                             val cryptoFilteredList: MutableLiveData<ArrayList<CryptoData>>,
                             val cryptoFilter: MutableLiveData<String>,
                             val cryptoPosition: MutableLiveData<Int>,
                             val cryptoGraph: MutableLiveData<Int>)