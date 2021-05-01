package org.eshendo.soopra.model

import com.squareup.moshi.Json

class Movie(
    val title: String = "",
    @field:Json(name = "poster_path")
    val poster: String = "",
    @field:Json(name = "overview")
    val description: String = ""
)