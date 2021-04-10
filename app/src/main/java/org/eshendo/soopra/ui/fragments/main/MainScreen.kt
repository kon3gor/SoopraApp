package org.eshendo.soopra.ui.fragments.main

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.facebook.shimmer.Shimmer
import com.google.android.material.snackbar.Snackbar
import org.eshendo.soopra.R
import org.eshendo.soopra.databinding.MainScreenBinding
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.ui.fragments.main.adapter.MoviesAdapter
import org.eshendo.soopra.ui.fragments.main.viewmodel.MainScreenState
import org.eshendo.soopra.ui.fragments.main.viewmodel.MainViewModel
import org.eshendo.soopra.ui.fragments.main.viewmodel.moviesFake
import org.eshendo.soopra.util.Screen
import org.eshendo.zebraapp.util.ViewBindingFragment
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

interface MainScreen : Screen<MainScreenState>{
    fun setupRecycler()
    fun setupShimer()
    fun setupListeners()
    fun movieClicked(m: Movie)
}

class MainScreenImpl : ViewBindingFragment<MainScreenBinding>(), MainScreen {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MainScreenBinding
        get() = MainScreenBinding::inflate

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var trendingAdapter: MoviesAdapter
    private lateinit var latestAdapter: MoviesAdapter

    override fun setup() {
        setupRecycler()
        observe()
        setupShimer()
        setupListeners()
        Handler(requireActivity().mainLooper).postDelayed({
            mainViewModel.requestData()
        }, 1000)
        updateState(MainScreenState.LoadingState)
    }

    override fun observe(){
        mainViewModel.viewState.observe(viewLifecycleOwner, Observer {
            updateState(it)
        })
    }


    override fun setupRecycler(){
        trendingAdapter = MoviesAdapter(listOf(), requireContext(), this::movieClicked)
        latestAdapter = MoviesAdapter(listOf(), requireContext(), this::movieClicked)
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

    override fun updateState(state: MainScreenState){
        when(state){
            MainScreenState.LoadingState -> {
                trendingAdapter.update(moviesFake, true)
                latestAdapter.update(moviesFake, true)
            }

            MainScreenState.RequestErrorState -> {
                Snackbar.make(binding.root, "Something went wrong", 2000).show()
            }

            MainScreenState.DataLoadedState -> toDataLoadedState()
        }
    }

    private fun toDataLoadedState(){
        val trending = mainViewModel.trendingMovies
        val latest = mainViewModel.latestReleases
        val filmOfTheDay = mainViewModel.filmOfTheDay

        trendingAdapter.update(trending, false)
        latestAdapter.update(latest, false)

        binding.filmOfTheDayVeil.unVeil()
        val fakeStr = getString(R.string.fake_description)
        animateFOTDDescription(fakeStr)
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