package org.eshendo.soopra.usecases.network

import androidx.lifecycle.LiveData
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.UseCaseResult
import org.eshendo.soopra.network.response.MoviesPageResponse
import org.eshendo.soopra.ui.fragments.main.viewmodel.MovieListPageLiveData
import org.eshendo.soopra.usecases.tasks.BaseTask
import org.eshendo.soopra.usecases.tasks.NetworkTask

class GetFilmOfTheDay  : NetworkUseCase<MoviesPageResponse>() {

    override var task: BaseTask = NetworkTask(repo.getTopRatedMovies())

    override fun execute(): MovieListPageLiveData {
        task = NetworkTask(repo.getTopRatedMovies())
        return run()
    }
}