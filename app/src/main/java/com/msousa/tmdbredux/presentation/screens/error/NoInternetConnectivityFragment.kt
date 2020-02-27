package com.msousa.tmdbredux.presentation.screens.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.msousa.tmdbredux.LayoutResource
import com.msousa.tmdbredux.presentation.screens.base.BaseFragment

class NoInternetConnectivityFragment private constructor() : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(LayoutResource.fragment_no_connectivity, container, false)
    }

    companion object { fun getInstance() = NoInternetConnectivityFragment() }
}