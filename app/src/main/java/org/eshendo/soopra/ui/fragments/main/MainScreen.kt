package org.eshendo.soopra.ui.fragments.main

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.annotation.UiThread
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.google.android.material.snackbar.Snackbar
import org.eshendo.soopra.R
import org.eshendo.soopra.databinding.MainScreenBinding
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.model.MovieList
import org.eshendo.soopra.repo.moviesFake
import org.eshendo.soopra.ui.fragments.main.adapter.MoviesAdapter
import org.eshendo.soopra.ui.fragments.main.viewmodel.MainViewModel
import org.eshendo.soopra.ui.fragments.main.viewmodel.MainViewModelImpl
import org.eshendo.soopra.util.IMAGE_URL
import org.eshendo.soopra.util.Screen
import org.eshendo.soopra.util.toast
import org.eshendo.zebraapp.util.ViewBindingFragment
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

interface MainScreen : Screen{
    fun setupRecycler()
    fun setupShimer()
    fun setupListeners()
    fun movieClicked(m: Movie)
    fun gotTrendingMovies(movies: MovieList)
    fun gotLatestMovies(movies: MovieList)
    fun gotFilmOfTheDay(movie: Movie)
}

class MainScreenImpl : ViewBindingFragment<MainScreenBinding>(), MainScreen {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MainScreenBinding
        get() = MainScreenBinding::inflate

    private val mainViewModel: MainViewModelImpl by viewModel()
    private lateinit var trendingAdapter: MoviesAdapter
    private lateinit var latestAdapter: MoviesAdapter

    override fun setup() {
        setupRecycler()
        observe()
        setupShimer()
        setupListeners()
    }

    override fun observe(){
        mainViewModel.getLatestReleases().observe(viewLifecycleOwner, Observer {
            it.data?.let { response ->
                gotLatestMovies(response.results)
            }

            it.error?.let { error ->
                toastError(error.toString())
            }
        })

        mainViewModel.getTrendingMovies().observe(viewLifecycleOwner, Observer {
            it.data?.let { response ->
                gotTrendingMovies(response.results)
                getRandomMovie(response.results)
            }

            it.error?.let { error ->
                toastError(error.toString())
            }
        })

    }

    override fun toastError(msg: String) {
        toast(msg)
    }

    private fun getRandomMovie(list: List<Movie>){
        val random = Random()
        val randInd = random.nextInt(list.size)
        gotFilmOfTheDay(list[randInd])
    }


    override fun setupRecycler(){
        trendingAdapter = MoviesAdapter(moviesFake, requireContext(), this::movieClicked)
        latestAdapter = MoviesAdapter(moviesFake, requireContext(), this::movieClicked)
        binding.trending.adapter = trendingAdapter
        binding.latest.adapter = latestAdapter
    }

    override fun setupShimer(){
        val shimmer = Shimmer.ColorHighlightBuilder()
                .setBaseColor(ContextCompat.getColor(requireContext(), R.color.mainPurple))
                .setHighlightColor(ContextCompat.getColor(requireContext(), R.color.highLight))
                .setBaseAlpha(1f)
                .setHighlightAlpha(1f)
                .build()

        binding.filmOfTheDayVeil.shimmer = shimmer
    }

    override fun setupListeners(){
        binding.allMoviesBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_allMoviesScreen)
        }
        binding.filmOfTheDayPoster.setOnClickListener { movieClicked(Movie()) }
    }

    override fun movieClicked(m: Movie) {
        findNavController().navigate(R.id.action_mainScreen_to_movieScreen)
    }

    override fun gotTrendingMovies(movies: MovieList){
        trendingAdapter.update(movies, false)
    }

    override fun gotLatestMovies(movies: MovieList) {
        latestAdapter.update(movies, false)
    }

    override fun gotFilmOfTheDay(movie: Movie) {
        binding.filmOfTheDayVeil.unVeil()
        if (movie.poster != ""){
            Glide.with(requireContext()).load(IMAGE_URL + movie.poster).placeholder(R.drawable.ic_placeholder).into(binding.filmOfTheDayPoster)
        }
        val fakeStr = getString(R.string.fake_description)
        val textToPrint =
            if (movie.description.length > fakeStr.length) {
                movie.description.substring(0..fakeStr.length).replaceRange(
                    fakeStr.length-2, fakeStr.length+1, "..."
                )
            } else movie.description
        animateFOTDDescription(textToPrint)
    }

    /**
     * Method for animating the description text for the film of the day
     */
    private fun animateFOTDDescription(text: String){
        val size = text.length
        var current = ""
        var ind = 0
        Timer().schedule(object : TimerTask(){
            override fun run() {
                current += text[ind++]
                if (ind == size) this.cancel()
                if (this@MainScreenImpl.isVisible){
                    updateDescription(current)
                }else{
                    this.cancel()
                }
            }
        }, 0, 20)
    }

    private fun updateDescription(newStr: String){
        requireActivity().runOnUiThread {  binding.filmOfTheDayDescription.text = newStr}
    }

}