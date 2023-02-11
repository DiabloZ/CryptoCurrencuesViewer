package com.suhov.cryptocurrencuesviewer.view.temp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_empty.*
import com.suhov.cryptocurrencuesviewer.R
import com.suhov.cryptocurrencuesviewer.utils.Constants

class EmptyFragmentTwo() : Fragment() {


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_empty, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        test_view.text = Constants.test
    }
}