package org.eshendo.soopra.repo

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.eshendo.soopra.db.MovieDao
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.MovieList
import org.eshendo.soopra.model.SavedMovie
import org.eshendo.soopra.model.SavedMovieList
import org.koin.core.component.KoinComponent

val moviesFake = listOf(
    Movie(), Movie(), Movie(), Movie(),
    Movie(), Movie(), Movie(), Movie(),
    Movie(), Movie(), Movie(), Movie()
)

interface LocalRepository : KoinComponent {
     fun getFakeMovies() : MovieList
     fun getFakeMOvie() : Movie
    suspend fun saveMovie(m: SavedMovie)
    suspend fun getAllMovies() : SavedMovieList
    suspend fun getMovieById(id: Long) : SavedMovie?
    suspend fun updateMovie(movie: SavedMovie)
}

class LocalRepositoryImpl(
    private val dao: MovieDao
)  : LocalRepository{
    override fun getFakeMovies(): MovieList {
        return moviesFake
    }

    override fun getFakeMOvie(): Movie {
        return Movie()
    }

    override suspend fun saveMovie(m: SavedMovie) {
        dao.insertNewMovie(m)
    }

    override suspend fun getAllMovies(): SavedMovieList {
        return dao.getAll()
    }

    override suspend fun getMovieById(id: Long): SavedMovie? {
        return dao.getById(id)
    }

    override suspend fun updateMovie(movie: SavedMovie) {
        return dao.updateMovie(movie)
    }

}
