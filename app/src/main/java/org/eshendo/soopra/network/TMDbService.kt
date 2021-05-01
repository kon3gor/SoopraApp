package org.eshendo.soopra.network

import io.reactivex.rxjava3.core.Observable
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.network.response.MoviesPageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbService {

    @GET("movie/now_playing")
    fun getLatestMovies(
            @Query("api_key") token: String
    ) : Observable<MoviesPageResponse>

    @GET("movie/popular")
    fun getTrendingMovies(
            @Query("api_key") token: String
    ) : Observable<MoviesPageResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
            @Query("api_key") token: String
    ) : Observable<MoviesPageResponse>

    @GET("movie/latest")
    fun getMovieOfTheDay(
        @Query("api_key") token: String
    ) : Observable<Movie>
}