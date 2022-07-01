package com.olvera.moviedb.api.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.olvera.moviedb.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie?)

    @Delete
    fun deleteMovie(movie: Movie?)

    @Query("SELECT * FROM movies")
    fun getAllMovies():LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): LiveData<Movie>

}