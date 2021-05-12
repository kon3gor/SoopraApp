package org.eshendo.soopra.ui.fragments.movie.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.UseCaseResult
import org.eshendo.soopra.usecases.local.SaveMovie
import org.eshendo.soopra.usecases.network.GetMovieDetails
import org.eshendo.soopra.util.BaseViewModel

interface  MovieViewModel {

    fun getDetails() : LiveData<UseCaseResult<Movie>>
    fun save(m: Movie)

}

class MovieViewModelImpl : MovieViewModel, BaseViewModel(){

    private val getDetails: GetMovieDetails by lazy {
        GetMovieDetails(id)
    }

    private val saveMovie = SaveMovie()

    var id: Long = -1L

    override fun getDetails(): LiveData<UseCaseResult<Movie>> {
        return getDetails.execute()
    }

    override fun save(m: Movie) {
        viewModelScope.launch {
            saveMovie.execute(m)
        }
    }
}