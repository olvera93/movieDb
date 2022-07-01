package com.olvera.moviedb.ui.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.olvera.moviedb.R
import com.olvera.moviedb.ui.detail.DetailViewModel
import com.olvera.moviedb.ui.movieRated.MovieAdapter
import com.olvera.moviedb.util.BaseViewModelFragment
import kotlinx.android.synthetic.main.fragment_favourite.*
import kotlinx.android.synthetic.main.fragment_movie_rated.*

/**
 * A simple [Fragment] subclass.
 * Use the [FavouriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouriteFragment : BaseViewModelFragment<DetailViewModel>() {


    override fun getViewModel(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllMovies().observe(viewLifecycleOwner, Observer {
            recyclerViewFavourite.layoutManager = GridLayoutManager(context, 1)
            recyclerViewFavourite.adapter = MovieAdapter(it) {
                val bundle = bundleOf("movie_details" to it)
                Navigation.findNavController(view)
                    .navigate(R.id.next_fragment, bundle)            }
        })
    }
}