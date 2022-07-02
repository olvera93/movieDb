package com.olvera.moviedb.ui.detail

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.airbnb.lottie.LottieAnimationView
import com.olvera.moviedb.R
import com.olvera.moviedb.api.remote.Api
import com.olvera.moviedb.api.repository.MovieRepository
import com.olvera.moviedb.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DetailViewModel(application: Application): AndroidViewModel(application) {

    private val api = Api() // Es para crear la instancia de la api a traves de la clase Api
    private val dipossable = CompositeDisposable() // Se crea una instancia de la clase CompositeDisposable para poder hacer dispose de los disposables que se creen en la clase

    private val repository: MovieRepository by lazy {
        MovieRepository(application.applicationContext) // Esto es una inyección de dependencias de Android (Application Context) para poder acceder a los datos de la base de datos local
    }

    fun insertMovie(movie: Movie) = repository.insertMovie(movie) // Se crea una función para insertar una pelicula en la base de datos local
    fun deleteMovie(movie: Movie) = repository.deleteMovie(movie) // Se crea una función para eliminar una pelicula en la base de datos local
    fun getMovieById(id: Int): LiveData<Movie> = repository.getMovieById(id) // Se crea una función para obtener una pelicula en la base de datos local
    fun getAllMovies(): LiveData<List<Movie>> = repository.getAllMovies() // Se crea una función para obtener todas las peliculas en la base de datos local

    val movieDetail = MutableLiveData<MovieDetail>() // Se crea una instancia de la clase MutableLiveData para poder hacer el bind con la vista
    val movieVideo = MutableLiveData<List<MovieVideo>>()

    fun getMovieDetails(movieId:Int?){ // Se crea una función para obtener los detalles de una pelicula
        dipossable.add( // Se agrega el disposable a la lista de disposables y este se encargará de hacer dispose de los disposables
            api.getMovieDetails(movieId!!)
                .subscribeOn(Schedulers.newThread()) // Se crea una instancia de la clase Schedulers para poder hacer el subscribe en un hilo diferente al principal
                .observeOn(AndroidSchedulers.mainThread()) // Se crea una instancia de la clase AndroidSchedulers para poder hacer el observe en el hilo principal
                .subscribeWith(object : DisposableSingleObserver<MovieDetail>(){ // Se crea una instancia de la clase DisposableSingleObserver para poder hacer el subscribe con la api
                    override fun onSuccess(t: MovieDetail) {
                        movieDetail.value = t
                        Log.i("Detail View Model" , "WORKING")

                    }

                    override fun onError(e: Throwable) {
                        Log.i("Detail View Model" , "NOT WORKING : "+e.message)
                    }

                })
        )
    }



    fun getMovieTrailers(movieId: Int?){
        dipossable.add(
            api.getMovieTrailers(movieId!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieVideoResponse>(){
                    override fun onSuccess(t: MovieVideoResponse) {
                        movieVideo.value = t.results
                    }
                    override fun onError(e: Throwable) {
                    }

                })
        )
    }

     fun likeAnimation(imageView: LottieAnimationView,
                              animation: Int,
                              like: Boolean) : Boolean {

        if (!like) {
            imageView.setAnimation(animation)
            imageView.playAnimation()
        } else {
            imageView.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animator: Animator) {
                        imageView.setImageResource(R.drawable.twitter_like)
                        imageView.alpha = 1f
                    }
                })
        }
        return !like
    }

    override fun onCleared() { // Se crea una función para limpiar los disposables
        super.onCleared()
        dipossable.clear() // Se limpia la lista de disposables
    }
}