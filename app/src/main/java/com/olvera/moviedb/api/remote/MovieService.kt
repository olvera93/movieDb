package com.olvera.moviedb.api.remote

import com.olvera.moviedb.model.MovieDetail
import com.olvera.moviedb.model.MovieResponse
import com.olvera.moviedb.model.MovieVideoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getPopularMovies(): Single<MovieResponse>

    @GET("movie/now_playing")
    fun getNowPlaying(): Single<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Single<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int): Single<MovieDetail>

    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") id: Int): Single<MovieVideoResponse>

    @GET("search/movie")
    fun searchMovies(@Query("query") search: String): Single<MovieResponse>

}