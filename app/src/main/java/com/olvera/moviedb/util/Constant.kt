package com.olvera.moviedb.util

object Constant {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "38ef64108127c75e24590c44c9513a57"

    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    const val IMAGE_W500 = "w500"

    const val MOVIE_DETAILS_BASE_URL = "https://www.youtube.com/watch?v="

}

fun buildYouTubeThumbnailURL(key: String): String { // Muestra la URL de la imagen
    return "https://img.youtube.com/vi/$key/0.jpg"
}

fun buildYoutubeURL(key: String): String {
    return "https://www.youtube.com/watch?v=" + key
}