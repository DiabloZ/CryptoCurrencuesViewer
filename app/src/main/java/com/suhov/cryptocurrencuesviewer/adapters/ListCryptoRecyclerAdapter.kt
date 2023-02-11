package com.suhov.cryptocurrencuesviewer.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.slide_crypto_list_item.view.*
import com.suhov.cryptocurrencuesviewer.R
import com.suhov.cryptocurrencuesviewer.modules.DataCryptoMutable
import com.suhov.cryptocurrencuesviewer.network.models.CryptoData
import com.suhov.cryptocurrencuesviewer.utils.Constants
import com.suhov.cryptocurrencuesviewer.view.MainFragmentDirections


class ListCryptoRecyclerAdapter(private val dataCryptoMutable: DataCryptoMutable) : RecyclerView.Adapter<ListCryptoRecyclerAdapter.ListCryptoViewHolder>() {
    private var items: List<CryptoData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCryptoViewHolder {
        return ListCryptoViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.slide_crypto_list_item, parent, false), dataCryptoMutable
        )
    }

    override fun onBindViewHolder(holder: ListCryptoViewHolder, position: Int) {holder.bind(items[position])}

    override fun getItemCount(): Int {return items.size}

    fun update(itemList: List<CryptoData>) {
        items = itemList
        notifyDataSetChanged()
    }

    class ListCryptoViewHolder constructor(
            itemView: View,
            dataCryptoMutable:DataCryptoMutable
    ) : RecyclerView.ViewHolder(itemView){
        private val imgCrypto = itemView.img_crypto
        private val titleCrypto = itemView.title_crypto
        private val fullNameCrypto = itemView.full_name_crypto
        private val priceCrypto = itemView.price_crypto
        private val marketDynamicsDay = itemView.market_dynamics_day
        private val marketDynamicsHour = itemView.market_dynamics_hour
        private val currencies = "$"
        private val percent = "%"
        private val cutToTwoSymbol = "%.2f"
        private val cutToFourSymbol = "%.4f"
        private val cryptoList = dataCryptoMutable.cryptoList.value

        init {
            itemView.setOnClickListener {
                val filteredList = dataCryptoMutable.cryptoFilteredList.value
                hideKeyboard()
                var positionTemp = 0
                for(i in cryptoList?.indices!!){
                     if (fullNameCrypto.text == cryptoList[i].name) positionTemp = i
                }
                dataCryptoMutable.cryptoPosition.value = positionTemp
                Log.d("getPositionData", "send: position - ${positionTemp}")
                Log.d("getPositionData", "send: position ID - ${cryptoList[positionTemp].id}")
                Log.d("getPositionData", "send: size ${cryptoList.size}")
                val action = MainFragmentDirections.actionListCryptoToDetailsViewFragment()
                Navigation.findNavController(it).navigate(action)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: CryptoData) {
            titleCrypto.text = item.symbol
            fullNameCrypto.text = item.name
            priceCrypto.text = currencies + String.format(cutToFourSymbol, item.price)
            marketDynamicsHour.text =  String.format(cutToTwoSymbol, item.percent_change_1h) + percent
            marketDynamicsDay.text =  String.format(cutToTwoSymbol, item.percent_change_24h) + percent

            Glide.with(itemView.context)
                    .load(Constants.IMG_URL_MAIN + item.imgURL)
                    .placeholder(R.drawable.please_stand_by)
                    .into(imgCrypto)
        }

        private fun hideKeyboard(){
            val iM = itemView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            iM.hideSoftInputFromWindow(itemView.windowToken,InputMethodManager.RESULT_UNCHANGED_SHOWN)
        }
    }

}