package org.eshendo.soopra.usecases.network

import androidx.lifecycle.LiveData
import org.eshendo.soopra.model.UseCaseResult
import org.eshendo.soopra.network.response.MoviesPageResponse
import org.eshendo.soopra.usecases.tasks.BaseTask
import org.eshendo.soopra.usecases.tasks.NetworkTask

class GetTopRatedMovies : NetworkUseCase<MoviesPageResponse>() {

    override var task: BaseTask = NetworkTask(repo.getTopRatedMovies())

    override fun execute(): LiveData<UseCaseResult<MoviesPageResponse>> {
        task = NetworkTask(repo.getLastestMovies())
        return run()
    }
}