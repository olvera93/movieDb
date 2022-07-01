package com.olvera.moviedb.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.olvera.moviedb.api.remote.Api
import com.olvera.moviedb.model.Movie
import com.olvera.moviedb.model.MovieResponse

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel(application: Application) : AndroidViewModel(application) { // AndroidViewModel es para el contexto de la aplicacion y no de la vista

    private val api = Api()
    private val disposable = CompositeDisposable()

    val popularMovie = MutableLiveData<List<Movie>>()
    val loadingMovies = MutableLiveData<Boolean>()


    fun getPopularMovies(){ // metodo para obtener los datos de la api
        loadingMovies.value = true
        disposable.add(
            api.getPopularMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieResponse>(){
                    override fun onSuccess(t: MovieResponse) {
                        popularMovie.value = t.results
                        loadingMovies.value = false
                        Log.i("HOME FRAGMENT : ", "WORKING")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("HOME FRAGMENT : ", "NOT WORKING ${e.message}")
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}