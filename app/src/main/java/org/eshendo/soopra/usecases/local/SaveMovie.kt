package org.eshendo.soopra.usecases.local

import androidx.lifecycle.LiveData
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.SavedMovie
import org.eshendo.soopra.model.UseCaseResult
import org.eshendo.soopra.usecases.tasks.BaseTask
import org.eshendo.soopra.usecases.tasks.LocalTask

class SaveMovie : SuspendUseCase<Movie, Unit>() {

    override suspend fun execute(data: Movie) {
        val saved = SavedMovie(data.id, data.poster)
        val check = repo.getMovieById(data.id)

        if (check != null) {
            repo.updateMovie(saved)
        }else{
            repo.saveMovie(saved)
        }
    }
}