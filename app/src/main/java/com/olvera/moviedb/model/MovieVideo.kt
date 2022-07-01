package com.olvera.moviedb.model

import com.google.gson.annotations.SerializedName

data class MovieVideo(
    @SerializedName("id")
    var id: String,

    @SerializedName("key")
    var key: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("site")
    var site: String
)
