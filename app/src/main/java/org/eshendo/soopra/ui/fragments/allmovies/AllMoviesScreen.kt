package org.eshendo.soopra.ui.fragments.allmovies

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import org.eshendo.soopra.R
import org.eshendo.soopra.databinding.AllMoviesScreenBinding
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.ui.fragments.allmovies.adapter.MoviesGridAdapter
import org.eshendo.soopra.ui.fragments.allmovies.viewmodel.AllMoviesScreenState
import org.eshendo.soopra.ui.fragments.allmovies.viewmodel.AllMoviesViewModel
import org.eshendo.soopra.ui.fragments.main.adapter.MoviesAdapter
import org.eshendo.soopra.ui.fragments.main.viewmodel.MainScreenState
import org.eshendo.soopra.ui.fragments.main.viewmodel.MainViewModel
import org.eshendo.soopra.util.Screen
import org.eshendo.zebraapp.util.ViewBindingFragment
import org.koin.android.viewmodel.ext.android.viewModel

interface AllMoviesScreen : Screen<AllMoviesScreenState>{
    fun setupGrid()
    fun setupListeners()
    fun movieCliced(m: Movie)
}

class AllMoviesScreenImpl : ViewBindingFragment<AllMoviesScreenBinding>(), AllMoviesScreen {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AllMoviesScreenBinding
        get() = AllMoviesScreenBinding::inflate

    private lateinit var moviesGridAdapter: MoviesGridAdapter
    private lateinit var moviesListAdapter: MoviesAdapter
    private val allMoviesViewModel: AllMoviesViewModel by viewModel()

    private val movies = listOf(
        Movie(), Movie(), Movie(), Movie(),
        Movie(), Movie(), Movie(), Movie(),
        Movie(), Movie(), Movie(), Movie()
    )

    override fun setup() {
        setupGrid()
        setupListeners()
        update()
    }

    override fun observe(){
        allMoviesViewModel.viewState.observe(viewLifecycleOwner, Observer {
            updateState(it)
        })
    }

    override fun setupGrid(){
        moviesGridAdapter = MoviesGridAdapter(movies, requireContext(), this::movieCliced)
        moviesListAdapter = MoviesAdapter(movies, requireContext(), this::movieCliced)

        binding.uWouldLikeMivies.adapter = moviesGridAdapter
        binding.topRatedMovies.adapter = moviesGridAdapter
        binding.lastSeenMovies.adapter = moviesListAdapter
    }

    override fun setupListeners(){
        binding.back.setOnClickListener { requireActivity().onBackPressed() }
    }

    override fun movieCliced(m: Movie) {
        findNavController().navigate(R.id.action_allMoviesScreen_to_movieScreen)
    }

    private fun update(){
        updateState(AllMoviesScreenState.LoadingState)
        Handler(requireActivity().mainLooper).postDelayed({
            if (this@AllMoviesScreenImpl.isAdded){
                updateState(AllMoviesScreenState.DataLoadedState(movies))
            }
        }, 4000)
    }

    override fun updateState(state: AllMoviesScreenState){
        when(state){
            AllMoviesScreenState.LoadingState -> {
                moviesGridAdapter.update(movies, true)
                moviesListAdapter.update(movies, true)
            }
            AllMoviesScreenState.ErrorState -> {
                Snackbar.make(binding.root, "Something went wrong", 2000).show()
            }

            is AllMoviesScreenState.DataLoadedState -> {
                toDataLoadedState(state.movies)
            }
        }
    }

    private fun toDataLoadedState(movies: List<Movie>) {
        moviesGridAdapter.update(movies, false )
        moviesListAdapter.update(movies, false )
    }
}