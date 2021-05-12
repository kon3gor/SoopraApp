package org.eshendo.soopra.db

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module

val databseModule = module {
    single { provideDatabase(get()) }
    single { provideMoviesDao(get()) }
}

private fun provideDatabase(context: Context) : MoviesDatabase{
    return Room.databaseBuilder(
        context,
        MoviesDatabase::class.java,
        "movies db"
    ).build()
}

private fun provideMoviesDao(db: MoviesDatabase) : MovieDao{
    return db.movieDao()
}