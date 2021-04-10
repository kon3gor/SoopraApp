package org.eshendo.soopra.ui.fragments.movie.viemodel

import androidx.lifecycle.ViewModel
import org.eshendo.soopra.util.BaseViewModel

abstract class MovieViewModel : BaseViewModel<MovieScreenState>() {

}

sealed class MovieScreenState {
    object LoadingState: MovieScreenState()
    object LoadedState: MovieScreenState()
}

class MovieViewModelImpl : MovieViewModel(){
}