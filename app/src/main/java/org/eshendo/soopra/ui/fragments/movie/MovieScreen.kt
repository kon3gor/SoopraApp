package org.eshendo.soopra.ui.fragments.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import org.eshendo.soopra.databinding.GenreItemBinding
import org.eshendo.soopra.databinding.MovieScreenBinding
import org.eshendo.zebraapp.util.ViewBindingFragment

interface MovieScreen{
    fun setupTags()
    fun requestMovie()
    fun setupListeners()
    fun toErrorState()
    fun toDataLoadedState()
}

class MovieScreenImpl : ViewBindingFragment<MovieScreenBinding>(),
    MovieScreen {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MovieScreenBinding
        get() = MovieScreenBinding::inflate

    override fun setup() {
        setupListeners()
        setupTags()
    }

    override fun setupTags() {
        val tags = listOf(
            "Drama", "Action", "History", "Documentary", "Fantasy"
        )

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
    }

    override fun requestMovie() {
        TODO("Not yet implemented")
    }

    override fun setupListeners() {
        binding.appbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }


    override fun toErrorState() {
        TODO("Not yet implemented")
    }

    override fun toDataLoadedState() {
        TODO("Not yet implemented")
    }
}