package org.eshendo.soopra.ui.fragments.allmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.eshendo.soopra.model.ErrorObject
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.util.BaseViewModel

abstract class AllMoviesViewModel : BaseViewModel<AllMoviesScreenState>(){

    protected var movies = listOf<Movie>()

    abstract fun requestMovies()
    abstract fun gotData(movies: List<Movie>)
    abstract fun onError(error: ErrorObject)
}

sealed class AllMoviesScreenState{
    object LoadingState : AllMoviesScreenState()
    object ErrorState : AllMoviesScreenState()
    class DataLoadedState(val movies: List<Movie>) : AllMoviesScreenState()
}

class AllMoviesViewModelImpl : AllMoviesViewModel() {

    override fun requestMovies() {
        TODO("Not yet implemented")
    }

    override fun gotData(movies: List<Movie>) {
        TODO("Not yet implemented")
    }

    override fun onError(error: ErrorObject) {
        TODO("Not yet implemented")
    }

}