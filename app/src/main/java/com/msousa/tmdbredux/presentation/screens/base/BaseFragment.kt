package com.msousa.tmdbredux.presentation.screens.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private val baseActivity by lazy { activity as? BaseActivity }

    val store by lazy { baseActivity?.store }
}