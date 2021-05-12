package org.eshendo.soopra.ui.fragments.allmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.eshendo.soopra.model.ErrorObject
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.MovieList
import org.eshendo.soopra.model.UseCaseResult
import org.eshendo.soopra.ui.fragments.main.viewmodel.MovieListPageLiveData
import org.eshendo.soopra.usecases.local.GetAllMovies
import org.eshendo.soopra.usecases.local.GetFakeMovies
import org.eshendo.soopra.usecases.network.GetTopRatedMovies
import org.eshendo.soopra.usecases.network.GetTrendingMovies
import org.eshendo.soopra.util.BaseViewModel

typealias LastSeenLiveData = LiveData<List<Movie>>

interface AllMoviesViewModel {

    fun getWouldLikeMovies() : MovieListPageLiveData
    fun getTopRated(): MovieListPageLiveData
    fun getLastSeenMovies()
}

class AllMoviesViewModelImpl : AllMoviesViewModel, BaseViewModel() {

    private val _lastSeen = MutableLiveData<MovieList>()
    val lastSeen: LiveData<MovieList> get() = _lastSeen

    private val getWouldLike = GetTrendingMovies()
    private val getTopRated = GetTopRatedMovies()
    private val getLastSeen = GetAllMovies()

    override fun getWouldLikeMovies(): MovieListPageLiveData {
        return getWouldLike.execute()
    }

    override fun getTopRated(): MovieListPageLiveData {
        return getTopRated.execute()
    }

    override fun getLastSeenMovies() {
        viewModelScope.launch {
            val result = getLastSeen.execute(Unit)
            _lastSeen.postValue(result)
        }
    }

}