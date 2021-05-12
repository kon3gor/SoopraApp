package org.eshendo.soopra.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.eshendo.soopra.model.SavedMovie
import org.eshendo.soopra.model.SavedMovieList

@Dao
interface MovieDao {

    @Query("select * from savedmovie")
    suspend fun getAll() : SavedMovieList

    @Insert
    suspend fun insertNewMovie(movie: SavedMovie)

    @Update
    suspend fun updateMovie(movie: SavedMovie)

    @Query("select * from savedmovie where id=:id")
    suspend fun getById(id: Long) : SavedMovie?

}