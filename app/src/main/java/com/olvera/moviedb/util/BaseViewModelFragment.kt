package com.olvera.moviedb.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseViewModelFragment<viewModel: ViewModel>: Fragment() { // BaseViewModelFragment es para que sea un fragment

    lateinit var viewModel: viewModel

    abstract fun getViewModel(): Class<viewModel>
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[getViewModel()]
    }
}