package com.olvera.moviedb.ui.movieRated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.olvera.moviedb.R
import com.olvera.moviedb.databinding.ItemMovieBinding
import com.olvera.moviedb.model.Movie

class MovieAdapter(val movieDetailList: List<Movie>, val onItemClick:(Movie)->Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemMovieBinding>(inflater, R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieDetailList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.movie = movieDetailList[position]
        holder.bind(movieDetailList[position],onItemClick)
    }

    class MovieViewHolder(var view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root) { // MovieViewHolder sirve para poder acceder a los datos de la vista

        fun bind(model: Movie, onItemClick: (Movie) -> Unit){ // La funcionalidad de bind es para poder acceder a los datos de la vista
            itemView.setOnClickListener { onItemClick(model) }
        }
    }

}