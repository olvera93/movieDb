package com.olvera.moviedb.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olvera.moviedb.databinding.ItemMovieBinding
import com.olvera.moviedb.model.Movie

class MovieDetailAdapter(private val movieDetail: List<Movie>,
                         val onItemClick:(Movie) -> Unit): RecyclerView.Adapter<MovieDetailAdapter.ViewHolder>() { // Se pasa los datos al adapter y se crea una función para que se pueda llamar en el onClick

    fun getItem(position: Int): Movie {
        return movieDetail[position]
    }

    override fun getItemCount(): Int {
        return movieDetail.size
    }

    fun onItemClick(movie: Movie) { // Se crea una función para que se pueda llamar en el onClick  y se pasa el dato del item seleccionado
        onItemClick(movie)
    }

    class ViewHolder(val view: ItemMovieBinding): RecyclerView.ViewHolder(view.root) {
       fun bind(movie: Movie) {
            view.movie = movie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { // Sirve para crear la vista del item

        val inflater = LayoutInflater.from(parent.context)

        val view = ItemMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { // Su función del onBindViewHolder es llenar la vista con los datos

        val movie = movieDetail[position]
        holder.bind(movie)
        holder.view.root.setOnClickListener { // Su función setOnClickListener es llamar a la función onItemClick y se pasa el dato del item seleccionado
            onItemClick(movie)
        }
    }
}


