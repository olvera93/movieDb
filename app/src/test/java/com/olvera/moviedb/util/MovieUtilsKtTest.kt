package com.olvera.moviedb.util

import com.olvera.moviedb.model.Movie
import org.junit.Assert.*
import org.junit.Test

class MovieUtilsKtTest {

    @Test
    fun getNumberOfMovies_empty_returnsZero(){
        val movies = listOf<Movie>()

        val result = getNumberOfMovies(movies)

        assertEquals(0, result)
    }


    @Test
    fun getNumberOfMovies_null_returnsZero(){
        val movies = null

        val result = getNumberOfMovies(movies)

        assertEquals(0, result)
    }

    @Test
    fun getNumberOfMovies_two_returnsTwo(){
        val movies = listOf(
            Movie(1, "title1", "overview1", "posterPath1", "backdropPath1", "releaseDate1", "popularity1", "voteAverage1", 2.0, 244, true, 24.2),
            Movie(2, "title2", "overview2", "posterPath2", "backdropPath2", "releaseDate2", "popularity2", "voteAverage2", 4.0, 246, true, 28.2)
        )

        val result = getNumberOfMovies(movies)

        assertEquals(2, result)
    }
}