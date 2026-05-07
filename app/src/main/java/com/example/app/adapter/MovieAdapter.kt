package com.example.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.databinding.ItemMovieBinding
import com.example.app.model.Movie

/**
 * MovieAdapter — jembatan antara data List<Movie> dan tampilan RecyclerView.
 */
class MovieAdapter(
    private val onItemClick: (Movie) -> Unit
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                tvTitle.text  = movie.title
                tvGenre.text  = movie.genre
                ratingBar.rating = movie.rating

                // Menggunakan Glide untuk memuat gambar dari URL ke ImageView
                Glide.with(itemView.context)
                    .load(movie.posterUrl)
                    .placeholder(android.R.drawable.ic_menu_gallery) // Gambar saat loading
                    .error(android.R.drawable.stat_notify_error)    // Gambar jika error
                    .into(ivPoster)

                cardMovie.setOnClickListener {
                    onItemClick(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
