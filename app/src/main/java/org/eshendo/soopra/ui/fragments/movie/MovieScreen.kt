package org.eshendo.soopra.ui.fragments.movie

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import org.eshendo.soopra.R
import org.eshendo.soopra.databinding.GenreItemBinding
import org.eshendo.soopra.databinding.MovieScreenBinding
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.ui.fragments.movie.viemodel.MovieViewModelImpl
import org.eshendo.soopra.util.IMAGE_URL
import org.eshendo.soopra.util.Screen
import org.eshendo.soopra.util.toast
import org.eshendo.zebraapp.util.ViewBindingFragment
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel

interface MovieScreen : Screen{
    fun setupListeners()
}

class MovieScreenImpl : ViewBindingFragment<MovieScreenBinding>(), MovieScreen {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MovieScreenBinding
        get() = MovieScreenBinding::inflate

    private val args: MovieScreenImplArgs by navArgs()

    private val movieVieModel: MovieViewModelImpl by viewModel()

    override fun setup() {

        movieVieModel.id = args.movieId
        setupListeners()
        observe()
    }

    override fun setupListeners() {
        binding.appbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    override fun observe() {
        movieVieModel.getDetails().observe(viewLifecycleOwner, Observer {
            it.data?.let { response ->
                movieVieModel.save(response)
                gotDetails(response)
            }
            it.error?.let { err ->
                toast(err.toString())
            }
        })
    }

    private fun gotDetails(m: Movie){
        binding.description.text = m.description

        val tags = m.genres.map { it.name }
        val ids = arrayListOf<Int>()
        tags.forEach { tag ->
            val view = GenreItemBinding.inflate(layoutInflater, null, false)
            view.title.text = tag
            val tmpId = ConstraintLayout.generateViewId()
            view.root.id = tmpId
            binding.genresContainer.addView(view.root)
            ids.add(tmpId)
        }
        binding.flow.referencedIds = ids.toIntArray()

        if (m.backdrop != ""){
            Glide.with(requireContext())
                .load(IMAGE_URL + m.backdrop)
                .placeholder(R.drawable.ic_placeholder)
                .fitCenter()
                .into(binding.backdrop)
        }

        binding.appbar.title = m.title
        binding.ratingCircle.drawNewAngle(m.rating)
        binding.rating.text = m.rating.toString()
    }
}