package com.olvera.moviedb.ui.playing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.olvera.moviedb.R
import com.olvera.moviedb.databinding.ItemMoviePlayingNowBinding
import com.olvera.moviedb.model.Movie

class PlayingNowAdapter(val playingNowList: List<Movie>, val onItemClick:(Movie)->Unit):
RecyclerView.Adapter<PlayingNowAdapter.ViewHolder>(){

    class ViewHolder(var view: ItemMoviePlayingNowBinding) : RecyclerView.ViewHolder(view.root) { // MovieViewHolder sirve para poder acceder a los datos de la vista
        fun bind(model: Movie, onItemClick: (Movie) -> Unit){ // La funcionalidad de bind es para poder acceder a los datos de la vista
            itemView.setOnClickListener { onItemClick(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemMoviePlayingNowBinding>(inflater, R.layout.item_movie_playing_now, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.nowPlaying = playingNowList[position]
        holder.bind(playingNowList[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return playingNowList.size
    }
}