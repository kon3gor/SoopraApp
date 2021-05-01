package org.eshendo.soopra.usecases.local

import androidx.lifecycle.LiveData
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.MovieList
import org.eshendo.soopra.model.UseCaseResult
import org.eshendo.soopra.usecases.tasks.BaseTask
import org.eshendo.soopra.usecases.tasks.LocalTask

class GetFakeMovies : LocalUseCase<MovieList>() {
    override var task: BaseTask = LocalTask(repo::getFakeMovies)
    override fun execute(): LiveData<UseCaseResult<MovieList>> {
        task = LocalTask(repo::getFakeMOvie)
        return run()
    }
}