package org.eshendo.soopra.ui.fragments.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.squareup.picasso.Picasso
import org.eshendo.soopra.R
import org.eshendo.soopra.databinding.MovieListItemBinding
import org.eshendo.soopra.model.Movie
import org.eshendo.soopra.util.IMAGE_URL

class MoviesAdapter(
    private var list: List<Movie>,
    private val context: Context,
    private val movieClicked: (Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MovieVH>() {

    private val layoutInflater = LayoutInflater.from(context)
    private var isVeiled: Boolean = true

    class MovieVH(
        private val binding: MovieListItemBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(movie: Movie, context: Context, veiled: Boolean, movieClicked: (Movie) -> Unit){
            if (veiled) {
                binding.veil.veil()
            }else{
                binding.veil.unVeil()
            }

            val shimmer = Shimmer.ColorHighlightBuilder()
                .setBaseColor(ContextCompat.getColor(context, R.color.mainPurple))
                .setHighlightColor(ContextCompat.getColor(context, R.color.highLight))
                .setBaseAlpha(1f)
                .setHighlightAlpha(1f)
                .build()

            binding.veil.shimmer = shimmer
            binding.root.setOnClickListener { movieClicked(movie) }

            if (movie.poster != ""){
                Glide.with(context).load(IMAGE_URL + movie.poster).placeholder(R.drawable.ic_placeholder).into(binding.poster)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val binding = MovieListItemBinding.inflate(layoutInflater, parent, false)
        return MovieVH(
            binding
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val cur = list[position]
        holder.bind(cur, context, isVeiled, movieClicked)
    }

    fun update(list: List<Movie>, veiled: Boolean){
        this.list = list
        this.isVeiled = veiled
        notifyDataSetChanged()
    }
}