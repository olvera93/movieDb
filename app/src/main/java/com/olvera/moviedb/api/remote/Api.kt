package com.olvera.moviedb.api.remote

import com.olvera.moviedb.model.MovieDetail
import com.olvera.moviedb.model.MovieResponse
import com.olvera.moviedb.model.MovieVideoResponse
import com.olvera.moviedb.util.Constant
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(RequestInterceptor()) // Add the interceptor to the OkHttpClient
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // RxJava - This is used for the asynchronous calling of the network call
        .client(httpClient)
        .build()
        .create(MovieService::class.java)

    fun getPopularMovies(): Single<MovieResponse> { // Aquí estamos usando la clase única de RxJava2 para obtener los datos de forma asíncrona
        return api.getPopularMovies()
    }

    fun getTopRatedMovies(): Single<MovieResponse> {
        return api.getTopRatedMovies()
    }

    fun getMovieDetails(movieId: Int): Single<MovieDetail> {
        return api.getMovieDetails(movieId)
    }

    fun getMovieTrailers(movieId: Int): Single<MovieVideoResponse> {
        return api.getMovieVideos(movieId)
    }

    fun searchMovies(search: String): Single<MovieResponse> {
        return api.searchMovies(search)
    }

    fun getMoviePlayingNow(): Single<MovieResponse> {
        return api.getNowPlaying()
    }
}