package com.olvera.moviedb.ui.search

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

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val api = Api()
    private val disposable = CompositeDisposable()

    val search = MutableLiveData<List<Movie>>()

    fun search(query: String) {
        disposable.add(
            api.searchMovies(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieResponse>() {
                    override fun onSuccess(t: MovieResponse) {
                        search.value = t.results
                        Log.i("SearchViewModel", "Success")
                    }

                    override fun onError(e: Throwable) {
                        Log.e("SearchViewModel", "Error: ${e.message}")
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}