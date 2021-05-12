package org.eshendo.soopra.model

import com.squareup.moshi.Json

class Movie(
    val id: Long = -1L,
    @field:Json(name = "original_title")
    val title: String = "",
    @field:Json(name = "poster_path")
    val poster: String = "",
    @field:Json(name = "overview")
    val description: String = "",
    @field:Json(name = "backdrop_path")
    val backdrop: String = "",
    val genres: List<Genre> = listOf(),
    @field:Json(name = "vote_average")
    val rating: Float = 0f
)