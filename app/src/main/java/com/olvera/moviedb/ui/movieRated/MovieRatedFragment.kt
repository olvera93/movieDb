package com.olvera.moviedb.ui.movieRated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.olvera.moviedb.R

import com.olvera.moviedb.util.BaseFragment
import com.olvera.moviedb.databinding.FragmentMovieRatedBinding
import kotlinx.android.synthetic.main.fragment_movie_rated.*

class MovieRatedFragment: BaseFragment<FragmentMovieRatedBinding, MovieRatedViewModel>() {

    private var _binding: FragmentMovieRatedBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentMovieRatedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieRated()

        viewModel.movieRated.observe(viewLifecycleOwner) {
            recyclerViewTopRated.visibility = View.VISIBLE
            recyclerViewTopRated.layoutManager = GridLayoutManager(context, 2)
            recyclerViewTopRated.adapter = MovieAdapter(it) {
                val bundle = bundleOf("movie_details" to it)
                Navigation.findNavController(view)
                    .navigate(R.id.next_fragment, bundle)            }
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getViewBinding(): Int {
        return R.layout.fragment_movie_rated
    }

    override fun getViewModel(): Class<MovieRatedViewModel> {
        return MovieRatedViewModel::class.java
    }

}