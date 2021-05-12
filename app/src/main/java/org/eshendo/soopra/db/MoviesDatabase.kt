package org.eshendo.soopra.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.eshendo.soopra.model.SavedMovie

@Database(entities = arrayOf(SavedMovie::class), version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
}