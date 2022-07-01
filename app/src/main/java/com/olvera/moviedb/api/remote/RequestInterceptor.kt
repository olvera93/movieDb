package com.olvera.moviedb.api.remote

import com.olvera.moviedb.util.Constant
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor: Interceptor { // Esta clase es para interceptar las peticiones que se hacen a la API
    override fun intercept(chain: Interceptor.Chain): Response { // Esta función es la que se encarga de interceptar las peticiones que se hacen a la API
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder() // Se crea una nueva url con la original y se le agrega el api key para que no se muestre en la url
            .addQueryParameter("api_key", Constant.API_KEY)
            .build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}