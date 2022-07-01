package com.olvera.moviedb.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.olvera.moviedb.R
import com.olvera.moviedb.databinding.ListItemGenreBinding
import com.olvera.moviedb.model.MovieGenre

class GenreAdapter(var genre: List<MovieGenre>): RecyclerView.Adapter<GenreAdapter.ViewHolder>() { // Esta clase es la que se encarga de crear los ViewHolder y de llenar los datos

    class ViewHolder(val binding: ListItemGenreBinding):RecyclerView.ViewHolder(binding.root) {  // Es el ViewHolder que se encarga de llenar los datos

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ListItemGenreBinding>(inflater, R.layout.list_item_genre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { // Aqui se llena el ViewHolder con los datos que le corresponden en la posicion que se le pase
        holder.binding.genre = genre[position]

    }

    override fun getItemCount(): Int { // Retorna el tamaño de la lista
        return genre.size
    }


}