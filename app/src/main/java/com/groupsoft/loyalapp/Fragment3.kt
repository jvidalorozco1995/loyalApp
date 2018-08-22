package com.groupsoft.loyalapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by JORGE-PC on 08/08/2018.
 */
class Fragment3: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.store_fragment, container, false)

    companion object {
        fun newInstance(): Fragment3 = Fragment3()
    }
}