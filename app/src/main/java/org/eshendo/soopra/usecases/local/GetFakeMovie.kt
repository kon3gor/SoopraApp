package org.eshendo.soopra.usecases.local

import androidx.lifecycle.LiveData
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.UseCaseResult
import org.eshendo.soopra.usecases.tasks.BaseTask
import org.eshendo.soopra.usecases.tasks.LocalTask

class GetFakeMovie : LocalUseCase<Movie>() {
    override var task: BaseTask = LocalTask(repo::getFakeMOvie)
    override fun execute(): LiveData<UseCaseResult<Movie>> {
        task = LocalTask(repo::getFakeMOvie)
        return run()
    }
}