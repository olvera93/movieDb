package com.olvera.moviedb.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.olvera.moviedb.api.repository.MovieRepository
import com.olvera.moviedb.model.Movie

class FakeMovieRepository(context: Context) : MovieRepository(context) {

    private var observableMovies = MutableLiveData<List<Movie>>()

    override fun deleteMovie(movie: Movie) {
        val newList: MutableList<Movie> = observableMovies.value?.toMutableList() ?: mutableListOf()
        newList.remove(movie)
        observableMovies.value = newList
    }

    override fun insertMovie(movie: Movie) {
        val newList: MutableList<Movie> = observableMovies.value?.toMutableList() ?: mutableListOf()
        newList.add(movie)
        observableMovies.value = newList
    }



}