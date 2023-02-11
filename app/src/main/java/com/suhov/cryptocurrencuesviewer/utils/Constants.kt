package com.suhov.cryptocurrencuesviewer.utils

class Constants {
    companion object {
        //List TAB of TabLayoutMediator for MainFragment
        val namesOfTabFragments = arrayListOf("List", "Empty", "Empty_Two")

        //Instance List
        val CRYPTO_LIST_URL: String = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/"
        val CRYPTO_DATA_URL: String = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id="
        val IMG_URL_MAIN:String = "https://www.cryptocompare.com"
        //apiKey of pro-api.coinmarketcap.com
        const val API_HEADER: String = "X-CMC_PRO_API_KEY"
        const val API_KEY: String = "ddf0fd32-4fd0-4a3d-84d7-1d8ed3abb011"

        //Img List
        val IMG_URL: String = "https://min-api.cryptocompare.com/data/all/coinlist" //coinlist
        //DetailsView Items
        val GRAPH_URL: String = "https://min-api.cryptocompare.com/data/" //histohour?fsym=BTC&tsym=USD&aggregate=1&limit=24

        //EmptyFragments
        val test: String = "test"
    }
}