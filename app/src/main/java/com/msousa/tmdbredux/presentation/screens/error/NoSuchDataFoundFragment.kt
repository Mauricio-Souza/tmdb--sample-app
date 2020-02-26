package com.msousa.tmdbredux.presentation.screens.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.msousa.tmdbredux.LayoutResource
import com.msousa.tmdbredux.presentation.screens.base.BaseFragment

class NoSuchDataFoundFragment private constructor() : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(LayoutResource.fragment_no_data_found, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object { fun getInstance() = NoSuchDataFoundFragment() }
}