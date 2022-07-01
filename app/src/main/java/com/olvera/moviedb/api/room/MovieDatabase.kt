package com.olvera.moviedb.api.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.olvera.moviedb.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object { // Esta clase es un singleton por lo que solo se crea una vez y se utiliza para acceder a la base de datos de manera global
        @Volatile
        var instance: MovieDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MovieDatabase { // Se crea una instancia de la base de datos si no existe, si existe se devuelve la instancia
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movies.db"
                ).build()
            }
            return instance as MovieDatabase
        }
    }
}