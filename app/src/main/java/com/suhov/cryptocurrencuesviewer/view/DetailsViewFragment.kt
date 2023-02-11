package com.suhov.cryptocurrencuesviewer.view

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.Utils
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.details_view_fragment.*
import kotlinx.android.synthetic.main.details_view_fragment.liner_chart_view
import com.suhov.cryptocurrencuesviewer.R
import com.suhov.cryptocurrencuesviewer.adapters.DetailViewAdapter
import com.suhov.cryptocurrencuesviewer.network.models.CryptoData
import com.suhov.cryptocurrencuesviewer.viewModel.DetailViewViewModel
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.get
import kotlin.math.abs

class DetailsViewFragment : Fragment() {
    private val viewModel by viewModel<DetailViewViewModel>()
    private var detailViewAdapter = get<DetailViewAdapter>()
    private var cryptoList:ArrayList<CryptoData> = ArrayList()
    private val emptyString = ""
    private var positionCrypto:Int = 0
    private val defaultPosition = 0
    private var graph:Int = 0
    private val offscreenPageLimit = 3
    private val pixMarginPage = 3
    private val linerChartTextSize = 16f

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.details_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        positionCrypto = viewModel.position.value!!
        setObserve()
        setUpTabLayoutCryptoGraph()
        setUpViewPagerCryptoData()
        setUpGraph()
    }
    private fun setPositionViewPager(){
        Log.i("getPositionData", "setPositionViewPager: $positionCrypto")
        Log.d("getPositionData", "received: size ${cryptoList.size}")
        view_pager2.currentItem = positionCrypto
    }
    private fun setObserve() {
        viewModel.showProgress.observe(viewLifecycleOwner, {
            if (it) {
                load_progress.visibility = VISIBLE
            } else {
                if (cryptoList.size != 0) {
                    if (cryptoList[positionCrypto].data_change_day.isNotEmpty() || cryptoList[positionCrypto].data_change_year.isNotEmpty()) {
                        Log.d("getPositionData", "viewModel.showProgress.observe:came position - $positionCrypto change position ID - ${cryptoList[positionCrypto].id} position name - ${cryptoList[positionCrypto].symbol}")
                        updateGraph()
                        setPositionViewPager()
                    }
                }
                load_progress.visibility = GONE
            }
        })

        viewModel.cryptoList.observe(viewLifecycleOwner, {
            cryptoList = it
            detailViewAdapter.update(it)
            Log.d("getPositionData", "viewModel.cryptoList.observe: $positionCrypto")
        })

        viewModel.position.observe(viewLifecycleOwner, {
            positionCrypto = it
            Log.d("getPositionData", "viewModel.position.observe: $positionCrypto")
            if (cryptoList.size != 0) {
                viewModel.getDetailViewItem()
            }

        })

        viewModel.graph.observe(viewLifecycleOwner, {
            graph = it
            Log.d("getPositionData", "viewModel.graph.observe: $positionCrypto")
            if(cryptoList.size != 0) {
                viewModel.getDetailViewItem()
            }
        })

    }

    private fun setUpTabLayoutCryptoGraph() {
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.changeGraph(tab!!.position)
                emptyValueGraph()
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        tab_layout.selectTab(tab_layout.getTabAt(graph))
        viewModel.changeGraph(graph)
    }

    private fun setUpViewPagerCryptoData() {
        Log.i("getPositionData", "setUpViewPagerCryptoData(): ${positionCrypto}")
        view_pager2.adapter = detailViewAdapter
        view_pager2.orientation = ViewPager2.ORIENTATION_VERTICAL
        view_pager2.clipToPadding = false
        view_pager2.clipChildren = false
        view_pager2.offscreenPageLimit = offscreenPageLimit
        view_pager2.getChildAt(defaultPosition)
                .overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(pixMarginPage))
        compositePageTransformer.addTransformer { _: View, position: Float ->
            abs(position)
        }
        view_pager2.setPageTransformer(compositePageTransformer)

        view_pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.changePosition(position)
            }
        })

        Log.i("getPositionData", "setUpTabLayoutCryptoGraph(): ${positionCrypto}")
        setPositionViewPager()
    }

    private fun setUpGraph() {
        liner_chart_view.axisRight.isEnabled = false
        liner_chart_view.axisLeft.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        liner_chart_view.setScaleEnabled(false)
        liner_chart_view.setPinchZoom(false)
        liner_chart_view.isDoubleTapToZoomEnabled = false
        liner_chart_view.setTouchEnabled(true)
        liner_chart_view.invalidate()

        liner_chart_view.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                graph_data.visibility = VISIBLE
                graph_data.text = e?.y.toString()
            }

            override fun onNothingSelected() {emptyValueGraph()}
        })
    }

    private fun updateGraph() {
        val arr = when (graph) {
            0 -> cryptoList[positionCrypto].data_change_day
            1 -> cryptoList[positionCrypto].data_change_week
            2 -> cryptoList[positionCrypto].data_change_month
            3 -> cryptoList[positionCrypto].data_change_three_month
            4 -> cryptoList[positionCrypto].data_change_six_month
            5 -> cryptoList[positionCrypto].data_change_year
            6 -> cryptoList[positionCrypto].data_change_allTime
            else -> cryptoList[positionCrypto].data_change_day
        }

        val lineEntries: ArrayList<Entry> = ArrayList()

        for (xx in arr.indices) {
            val y: Float = arr[xx].close?.toFloat()!!
            val x: Float = xx.toFloat()
            lineEntries.add(Entry(x, y))
        }
        val lineDataSet = LineDataSet(lineEntries, cryptoList[positionCrypto].name)

        lineDataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        lineDataSet.valueTextColor = Color.BLACK
        lineDataSet.valueTextSize = linerChartTextSize
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawCircles(false)

        liner_chart_view.data = LineData(lineDataSet)
        liner_chart_view.invalidate()
        Log.i("getPositionData", "setUpGraph(): ${positionCrypto}")
        setPositionViewPager()
    }
    private fun emptyValueGraph() {
        graph_data.text = emptyString
    }
}