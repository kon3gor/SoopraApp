@file:Suppress("PropertyName")

package org.eshendo.soopra.ui.fragments.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.util.BaseViewModel
import java.util.concurrent.atomic.AtomicInteger

val moviesFake = listOf(
    Movie(), Movie(), Movie(), Movie(),
    Movie(), Movie(), Movie(), Movie(),
    Movie(), Movie(), Movie(), Movie()
)

abstract class MainViewModel : BaseViewModel<MainScreenState>(){

    var trendingMovies = listOf<Movie>()
    var latestReleases = listOf<Movie>()
    var filmOfTheDay: Movie? = null

    abstract fun requestData()
    protected abstract fun getTrendingMovies()
    protected abstract fun getLatestReleases()
    protected abstract fun getFilmOfTheDay()
    protected abstract fun gotData()
    protected abstract fun onError()
}

sealed class MainScreenState {
    object LoadingState : MainScreenState()
    object RequestErrorState : MainScreenState()
    object DataLoadedState : MainScreenState()
}

class MainViewModelImpl : MainViewModel() {

    private val count = AtomicInteger(0)

    override fun requestData() {
        getTrendingMovies()
        getLatestReleases()
        getFilmOfTheDay()
    }

    override fun getTrendingMovies() {
        trendingMovies = moviesFake
        gotData()
    }

    override fun getLatestReleases() {
        latestReleases = moviesFake
        gotData()
    }

    override fun getFilmOfTheDay() {
        gotData()
    }

    override fun gotData() {
        if (count.incrementAndGet() == 3){
             _viewState.postValue(MainScreenState.DataLoadedState)
        }
    }

    override fun onError() {
        _viewState.postValue(MainScreenState.RequestErrorState)
    }

}