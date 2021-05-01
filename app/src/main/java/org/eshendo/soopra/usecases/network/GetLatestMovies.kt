package org.eshendo.soopra.usecases.network

import androidx.lifecycle.LiveData
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.MovieList
import org.eshendo.soopra.model.UseCaseResult
import org.eshendo.soopra.network.response.MoviesPageResponse
import org.eshendo.soopra.usecases.tasks.BaseTask
import org.eshendo.soopra.usecases.tasks.NetworkTask

class GetLatestMovies : NetworkUseCase<MoviesPageResponse>() {

    override var task: BaseTask = NetworkTask(repo.getLastestMovies())

    override fun execute(): LiveData<UseCaseResult<MoviesPageResponse>> {
        task = NetworkTask(repo.getLastestMovies())
        return run()
    }

}