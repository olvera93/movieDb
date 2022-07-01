package com.olvera.moviedb.api.repository

import android.content.Context
import android.os.AsyncTask
import com.olvera.moviedb.api.room.MovieDao
import com.olvera.moviedb.api.room.MovieDatabase
import com.olvera.moviedb.model.Movie

open class MovieRepository(context: Context) {

    private val dataBase: MovieDatabase by lazy { // Esta es la forma de inicializar una variable de clase en Kotlin (lazy) y que se inicialice solo cuando se utilice
        MovieDatabase.getInstance(context)
    }

    private val movieDao: MovieDao by lazy {
        dataBase.movieDao()
    }


    fun getAllMovies() = movieDao.getAllMovies()

    fun getMovieById(id: Int) = movieDao.getMovieById(id)

    open fun insertMovie(movie: Movie) { // Esta función es para insertar una película en la base de datos
        InsertMovieAsyncTask(movieDao).execute(movie) // Ejecutamos la función en un hilo diferente
    }

    open fun deleteMovie(movie: Movie) { // Esta función es para borrar una película en la base de datos
        DeleteMovieAsyncTask(movieDao).execute(movie) // Ejecutamos la función en un hilo diferente
    }

    private class InsertMovieAsyncTask(private val movieDao: MovieDao) :
        AsyncTask<Movie, Void, Void>() { // Esta clase es para insertar una película en la base de datos en un hilo diferente (AsyncTask) y no bloquear el hilo principal

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg params: Movie): Void? {
            movieDao.insertMovie(params[0]) // Insertamos la película en la base de datos
            return null
        }
    }

    private class DeleteMovieAsyncTask(private val movieDao: MovieDao) :
        AsyncTask<Movie, Void, Void>() { // Esta clase es para borrar una película en la base de datos en un hilo diferente (AsyncTask) y no bloquear el hilo principal

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg params: Movie): Void? {
            movieDao.deleteMovie(params[0]) // Borramos la película pasada por parámetro
            return null
        }
    }
}