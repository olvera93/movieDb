package com.olvera.moviedb.util

import com.olvera.moviedb.model.Movie

internal fun getNumberOfMovies(movies: List<Movie>?): Int{
    return movies?.size ?: 0
}