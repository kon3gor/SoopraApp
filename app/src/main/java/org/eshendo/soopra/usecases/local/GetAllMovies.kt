package org.eshendo.soopra.usecases.local

import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.MovieList
import org.eshendo.soopra.model.SavedMovieList

class GetAllMovies : SuspendUseCase<Unit, List<Movie>>() {

    private fun toMovieList(list: SavedMovieList) : MovieList{
        return list.map { Movie(id = it.id, poster = it.posterPath) }
    }

    override suspend fun execute(data: Unit): List<Movie> {
        return toMovieList(repo.getAllMovies())
    }
}