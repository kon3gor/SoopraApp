package org.eshendo.soopra.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedMovie(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "poster_path")
    val posterPath: String
)