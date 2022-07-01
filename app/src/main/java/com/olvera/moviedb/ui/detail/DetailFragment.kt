package com.olvera.moviedb.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.olvera.moviedb.R
import com.olvera.moviedb.util.BaseFragment
import com.olvera.moviedb.databinding.FragmentMovieDetailBinding
import com.olvera.moviedb.model.Movie
import com.olvera.moviedb.util.Constant
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class DetailFragment : BaseFragment<FragmentMovieDetailBinding, DetailViewModel>() { // Esta clase es la que se encarga de mostrar los detalles de una película en concreto

    private var _binding: FragmentMovieDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var genreAdapter: GenreAdapter // Esta variable es la que se encarga de mostrar los géneros de una película en concreto
    private var movie: Movie? = null // Esta variable es la que se encarga de almacenar los datos de una película en concreto

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        arguments?.let { // Esta función es la que se encarga de recoger los datos de la película en concreto que se le pasa como argumento al fragmento de detalle
            movie = it.getParcelable("movie_details")
            viewBinding.detail = movie
            like()
        }

        return viewBinding.root
    }
    private var like: Boolean = false // Esta variable es la que se encarga de almacenar si una película está o no en la lista de favoritos

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { // Es la función que se encarga de mostrar los datos de la película
        super.onViewCreated(view, savedInstanceState)

        var movieDetail = arguments?.getParcelable<Movie>("movie_details") // Hacemos una llamada a la función que recoge los datos de la película en concreto

        viewModel.getMovieDetails(movieDetail?.movieId)
        viewModel.movieDetail.observe(viewLifecycleOwner, Observer { // Se muetra los datos de la película que se escogió para ver sus detalle
            it?.let { // Si la película existe se muestran los datos de la película
                viewBinding.content = it
                genreAdapter = GenreAdapter(it.genres!!)
                recyclerviewGenres.adapter = genreAdapter
            }
        })

        viewModel.getMovieTrailers(movieDetail?.movieId)

        viewModel.movieVideo.observe(viewLifecycleOwner, Observer {
            it?.let {
                recyclerviewTrailer.adapter = TrailerAdapter(it) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(Constant.MOVIE_DETAILS_BASE_URL + it.key)
                    startActivity(intent)
                }
            }
        })
    }

    private fun favMovie() {
        if (!like) {
            viewModel.deleteMovie(movie!!)
            Toasty.error(requireContext(), R.string.favorite_movie_removed, Toast.LENGTH_SHORT).show()
        } else {
            viewModel.insertMovie(movie!!)
            Toasty.success(requireContext(), R.string.favorite_movie_added, Toast.LENGTH_SHORT).show()
        }
    }

    private fun like() {
        movie?.let {
            viewModel.getMovieById(it.movieId).observe(viewLifecycleOwner, Observer {
                viewBinding.likeImageView.setOnClickListener {
                    like = viewModel.likeAnimation(likeImageView, R.raw.hmm, like)
                    favMovie()
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getViewBinding(): Int { // Esta función es la que se encarga de mostrar el layout de la vista
        return R.layout.fragment_movie_detail
    }

    override fun getViewModel(): Class<DetailViewModel> { // Esta función es la que se encarga de mostrar el viewModel de la vista
        return DetailViewModel::class.java
    }
}