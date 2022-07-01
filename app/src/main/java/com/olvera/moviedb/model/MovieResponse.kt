package com.olvera.moviedb.model

import com.google.gson.annotations.SerializedName

data class MovieResponse ( // data class to hold the response from the API
    @SerializedName("results")
    val results: List<Movie>) // List of Movie objects
