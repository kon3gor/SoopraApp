package org.eshendo.soopra.usecases.network

import androidx.lifecycle.LiveData
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.UseCaseResult
import org.eshendo.soopra.network.response.MoviesPageResponse
import org.eshendo.soopra.usecases.tasks.BaseTask
import org.eshendo.soopra.usecases.tasks.NetworkTask

class GetMovieDetails(private val id: Long) : NetworkUseCase<Movie>() {

    override var task: BaseTask = NetworkTask(repo.getDetails(id))

    override fun execute(): LiveData<UseCaseResult<Movie>> {
        task = NetworkTask(repo.getDetails(id))
        return run()
    }

}