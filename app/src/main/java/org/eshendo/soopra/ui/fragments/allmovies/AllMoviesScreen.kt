package org.eshendo.soopra.ui.fragments.allmovies

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import org.eshendo.soopra.R
import org.eshendo.soopra.databinding.AllMoviesScreenBinding
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.UseCaseResult
import org.eshendo.soopra.repo.moviesFake
import org.eshendo.soopra.ui.fragments.allmovies.adapter.MoviesGridAdapter
import org.eshendo.soopra.ui.fragments.allmovies.viewmodel.AllMoviesViewModel
import org.eshendo.soopra.ui.fragments.allmovies.viewmodel.AllMoviesViewModelImpl
import org.eshendo.soopra.ui.fragments.main.MainScreenImplDirections
import org.eshendo.soopra.ui.fragments.main.adapter.MoviesAdapter
import org.eshendo.soopra.usecases.network.GetTopRatedMovies
import org.eshendo.soopra.util.Screen
import org.eshendo.soopra.util.toast
import org.eshendo.zebraapp.util.ViewBindingFragment
import org.koin.android.viewmodel.ext.android.viewModel

interface AllMoviesScreen : Screen{
    fun setupGrid()
    fun setupListeners()
    fun movieCliced(m: Movie)
}

class AllMoviesScreenImpl : ViewBindingFragment<AllMoviesScreenBinding>(), AllMoviesScreen {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AllMoviesScreenBinding
        get() = AllMoviesScreenBinding::inflate

    private lateinit var topRatedAdapter: MoviesGridAdapter
    private lateinit var wouldLikeAdapter: MoviesGridAdapter
    private lateinit var lastSeenAdapter: MoviesAdapter
    private val allMoviesViewModel: AllMoviesViewModelImpl by viewModel()


    override fun setup() {
        setupGrid()
        setupListeners()
        observe()
        allMoviesViewModel.getLastSeenMovies()
    }

    override fun observe(){
        allMoviesViewModel.apply {
            getWouldLikeMovies().observe(viewLifecycleOwner, Observer {
                it.data?.let { resp ->
                    gotWouldLike(resp.results)
                }

                it.error?.let { err ->
                    toast(err.toString())
                }
            })

            getTopRated().observe(viewLifecycleOwner, Observer{
                it.data?.let { resp ->
                    gotTopRated(resp.results)
                }

                it.error?.let { err ->
                    toast(err.toString())
                }
            })

            lastSeen.observe(viewLifecycleOwner, Observer {
                gotLastSeen(it)
            })
        }
    }

    fun toastError(msg: String) {
        toast(msg)
    }

    private fun gotTopRated(movies: List<Movie>){
        topRatedAdapter.update(movies, false)
    }

    private fun gotWouldLike(movies: List<Movie>){
        wouldLikeAdapter.update(movies, false)
    }

    private fun gotLastSeen(movies: List<Movie>){
        lastSeenAdapter.update(movies, false)
    }

    override fun setupGrid(){
        topRatedAdapter = MoviesGridAdapter(moviesFake, requireContext(), this::movieCliced)
        wouldLikeAdapter = MoviesGridAdapter(moviesFake, requireContext(), this::movieCliced)
        lastSeenAdapter = MoviesAdapter(moviesFake, requireContext(), this::movieCliced)

        binding.uWouldLikeMivies.adapter = wouldLikeAdapter
        binding.topRatedMovies.adapter = topRatedAdapter
        binding.lastSeenMovies.adapter = lastSeenAdapter
    }

    override fun setupListeners(){
        binding.back.setOnClickListener { requireActivity().onBackPressed() }
    }

    override fun movieCliced(m: Movie) {
        if (m.id != -1L){
            val action = AllMoviesScreenImplDirections.actionAllMoviesScreenToMovieScreen(m.id)
            findNavController().navigate(action)
        }
    }

}