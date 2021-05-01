package org.eshendo.soopra.network.response

import org.eshendo.soopra.model.MovieList

class MoviesPageResponse(
        val page: Int,
        val results: MovieList
)