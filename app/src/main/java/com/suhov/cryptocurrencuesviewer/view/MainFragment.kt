package com.suhov.cryptocurrencuesviewer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import com.suhov.cryptocurrencuesviewer.R
import com.suhov.cryptocurrencuesviewer.adapters.StateAdapter
import com.suhov.cryptocurrencuesviewer.utils.Constants

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager2WithFragments()
    }

    private fun initViewPager2WithFragments() {
        val viewPager: ViewPager2 = requireView().liner_chart_view
        viewPager.adapter = StateAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(tab_layout, viewPager){tab, position ->
            tab.text = Constants.namesOfTabFragments[position]
        }.attach()
    }
}