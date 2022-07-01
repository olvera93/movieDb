package com.olvera.moviedb.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.olvera.moviedb.R
import com.olvera.moviedb.databinding.ListItemMovieBinding
import com.olvera.moviedb.model.Movie

class SearchAdapter(val searchList: List<Movie>, val onItemClick:(Movie)->Unit):
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() { // SearchAdapter es para el recyclerView

    class ViewHolder(val binding: ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Movie, onItemClick: (Movie) -> Unit){
            itemView.setOnClickListener { onItemClick(model) }
        }
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(searchList[position],onItemClick)
        holder.binding.movie = searchList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: ListItemMovieBinding = DataBindingUtil.inflate(inflater, R.layout.list_item_movie, parent, false)

        return ViewHolder(view)
    }
}
