package com.olvera.moviedb.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.olvera.moviedb.R
import com.olvera.moviedb.databinding.TrailerSingleItemBinding
import com.olvera.moviedb.model.MovieVideo
import com.olvera.moviedb.util.buildYouTubeThumbnailURL

class TrailerAdapter(var trailer: List<MovieVideo>, val trailerOnClick: (MovieVideo)->Unit) : RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {



    class ViewHolder(val binding: TrailerSingleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var mVideoImage: ImageView // Es una variable que se usa para poder establecer una imagen en el trailer
        init{
            mVideoImage = itemView.findViewById(R.id.activity_detail_trailer_poster_image) // Se establece la variable mVideoImage con el ImageView que se encuentra en el layout
        }
        fun bind(trailer: MovieVideo, trailerOnClick: (MovieVideo) -> Unit){
            itemView.setOnClickListener { trailerOnClick(trailer) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: TrailerSingleItemBinding = DataBindingUtil.inflate(inflater, R.layout.trailer_single_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoViewHolder = holder as ViewHolder
        val movieVideo: MovieVideo = trailer[holder.adapterPosition]

        Glide.with(holder.mVideoImage.context).load(buildYouTubeThumbnailURL(movieVideo.key!!)) // carga la imagen del video
                .thumbnail(0.05f).apply(
                    RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)) // no se guarda en cache
                .transition(DrawableTransitionOptions.withCrossFade()).into(videoViewHolder.mVideoImage) // se carga en el imageView del video (holder.mVideoImage) de la vista

        videoViewHolder.bind(movieVideo, trailerOnClick)
    }

    override fun getItemCount(): Int {
        return trailer.size
    }
}