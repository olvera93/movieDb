package com.olvera.moviedb.util

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<vBinding : ViewDataBinding, viewModel : ViewModel> : Fragment() { // BaseFragment es para que todos los fragments hereden de esta clase

     lateinit var viewBinding: vBinding
     lateinit var viewModel: viewModel

    abstract fun getViewBinding(): Int
    abstract fun getViewModel(): Class<viewModel>

    override fun onCreate(savedInstanceState: Bundle?) { // sirve para inicializar el viewModel y el binding
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[getViewModel()]
    }

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View? {
        viewBinding = DataBindingUtil.inflate(inflater, getViewBinding(), container, false)
        return viewBinding.root
    }



}

