package com.olvera.moviedb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.olvera.moviedb.R
import com.olvera.moviedb.util.BaseViewModelFragment
import com.olvera.moviedb.util.DepthPageTransformer

import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseViewModelFragment<HomeViewModel>() { // BaseViewHolderFragment su función es la que se encarga de inicializar el ViewModel

    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getPopularMovies()

        viewModel.popularMovie.observe(viewLifecycleOwner, Observer { popularMovie ->
            popularMovie?.let {
                lytHome.visibility = View.VISIBLE
                pagerPopularMovie.adapter = HomeAdapter(popularMovie)
                pagerPopularMovie.offscreenPageLimit = 3
                pagerPopularMovie.setPageTransformer(true, DepthPageTransformer())

            }
        })

        viewModel.loadingMovies.observe(viewLifecycleOwner, Observer { loadingMovies ->
            loadingMovies?.let {
                if (it){
                    homeProgressBar.visibility = View.VISIBLE
                    lytHome.visibility = View.GONE
                }else{
                    homeProgressBar.visibility = View.GONE
                }
            }
        })

    }
}
