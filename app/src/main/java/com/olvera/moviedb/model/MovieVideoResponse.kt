package com.olvera.moviedb.model

import com.google.gson.annotations.SerializedName

data class MovieVideoResponse(
    @SerializedName("id")
    var id: Int,

    @SerializedName("results")
    var results: List<MovieVideo>
)
