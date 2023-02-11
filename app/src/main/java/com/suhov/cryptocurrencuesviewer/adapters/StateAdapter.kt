package com.suhov.cryptocurrencuesviewer.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.suhov.cryptocurrencuesviewer.view.temp.EmptyFragment
import com.suhov.cryptocurrencuesviewer.view.temp.EmptyFragmentTwo
import com.suhov.cryptocurrencuesviewer.view.ListCrypto

class StateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

    private var fragments = arrayListOf(
            ListCrypto(),
            EmptyFragment(),
            EmptyFragmentTwo()
    )
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}