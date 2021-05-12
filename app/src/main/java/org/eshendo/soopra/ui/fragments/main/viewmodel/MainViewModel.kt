@file:Suppress("PropertyName", "UNCHECKED_CAST")

package org.eshendo.soopra.ui.fragments.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.MovieList
import org.eshendo.soopra.model.UseCaseResult
import org.eshendo.soopra.network.response.MoviesPageResponse
import org.eshendo.soopra.repo.moviesFake
import org.eshendo.soopra.usecases.local.GetFakeMovie
import org.eshendo.soopra.usecases.local.GetFakeMovies
import org.eshendo.soopra.usecases.network.GetFilmOfTheDay
import org.eshendo.soopra.usecases.network.GetLatestMovies
import org.eshendo.soopra.usecases.network.GetTrendingMovies
import org.eshendo.soopra.util.BaseViewModel
import java.util.concurrent.atomic.AtomicInteger

typealias MovieListPageLiveData = LiveData<UseCaseResult<MoviesPageResponse>>
typealias MovieLiveData = LiveData<UseCaseResult<Movie>>

interface MainViewModel{

    fun getTrendingMovies() : MovieListPageLiveData
    fun getLatestReleases(): MovieListPageLiveData

}

class MainViewModelImpl: MainViewModel, BaseViewModel(){

    private val getFakeMovie = GetFakeMovie()
    private val getTrendingMovies = GetTrendingMovies()
    private val getLatestMovies = GetLatestMovies()
    private val getFilmOfTheDay = GetFilmOfTheDay()

    override fun getTrendingMovies(): MovieListPageLiveData {
        return getTrendingMovies.execute()
    }

    override fun getLatestReleases(): MovieListPageLiveData {
        return getLatestMovies.execute()
    }



}