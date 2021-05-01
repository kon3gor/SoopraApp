package org.eshendo.soopra.repo

import io.reactivex.rxjava3.core.Observable
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.MovieList
import org.eshendo.soopra.network.TMDbService
import org.eshendo.soopra.network.response.MoviesPageResponse
import org.eshendo.soopra.usecases.tasks.NetworkTask
import org.eshendo.soopra.util.API_KEY
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call

interface NetworkRepository : KoinComponent {
    fun getTrendingMovies() : Observable<MoviesPageResponse>
    fun getTopRatedMovies() : Observable<MoviesPageResponse>
    fun getLastestMovies() : Observable<MoviesPageResponse>
    fun getFilmOfTheDay() : Observable<Movie>
}

class NetworkRepositoryImpl(
        private val api: TMDbService
) : NetworkRepository{

    override fun getTrendingMovies(): Observable<MoviesPageResponse> {
        return api.getTrendingMovies(API_KEY)
    }

    override fun getTopRatedMovies(): Observable<MoviesPageResponse> {
        return api.getTopRatedMovies(API_KEY)
    }

    override fun getLastestMovies(): Observable<MoviesPageResponse> {
        return api.getLatestMovies(API_KEY)
    }

    override fun getFilmOfTheDay(): Observable<Movie> {
        return api.getMovieOfTheDay(API_KEY)
    }
}