package com.olvera.moviedb.ui.playing

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olvera.moviedb.api.remote.Api
import com.olvera.moviedb.model.Movie
import com.olvera.moviedb.model.MovieResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PlayingNowViewModel(application: Application): AndroidViewModel(application) {

    private val api = Api()
    private val disposable = CompositeDisposable()

    val moviewPlayingNow = MutableLiveData<List<Movie>>()

    fun getMoviePlayingNow(){
        disposable.add(
            api.getMoviePlayingNow()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieResponse>() {
                    override fun onSuccess(t: MovieResponse) {
                        moviewPlayingNow.value = t.results
                        Log.i("NOW PLAYING VIEW MODEL", "WORKING")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("NOW PLAYING VIEW MODEL", "NOT WORKING ${e.message}")
                    }
                })
        )
    }
}