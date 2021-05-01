package org.eshendo.soopra.repo

import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.MovieList
import org.koin.core.component.KoinComponent

val moviesFake = listOf(
    Movie(), Movie(), Movie(), Movie(),
    Movie(), Movie(), Movie(), Movie(),
    Movie(), Movie(), Movie(), Movie()
)

interface LocalRepository : KoinComponent {
    fun getFakeMovies() : MovieList
    fun getFakeMOvie() : Movie
}

class LocalRepositoryImpl  : LocalRepository{
    override fun getFakeMovies(): MovieList {
        return moviesFake
    }

    override fun getFakeMOvie(): Movie {
        return Movie()
    }

}