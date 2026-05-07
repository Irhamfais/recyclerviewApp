package com.example.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.ItemMovieBinding
import com.example.app.model.Movie

/**
 * MovieAdapter — jembatan antara data List<Movie> dan tampilan RecyclerView.
 *
 * Menggunakan ListAdapter (bukan RecyclerView.Adapter biasa) karena:
 *   - Sudah terintegrasi dengan DiffUtil secara otomatis
 *   - Mendukung animasi perubahan item (insert, remove, update)
 *   - Perbandingan dilakukan di AsyncListDiffer (background thread) → UI tidak freeze
 *
 * @param onItemClick Lambda callback yang dipanggil saat item diklik.
 *                    Pola ini lebih bersih daripada interface listener.
 */
class MovieAdapter(
    private val onItemClick: (Movie) -> Unit
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    // =========================================================================
    // ViewHolder — menyimpan referensi View untuk satu item agar tidak di-query
    //              berulang kali saat RecyclerView melakukan scroll (recycle).
    // Dideklarasikan sebagai 'inner class' agar bisa mengakses onItemClick.
    // =========================================================================
    inner class MovieViewHolder(
        private val binding: ItemMovieBinding   // ViewBinding: type-safe, auto-generated
    ) : RecyclerView.ViewHolder(binding.root) {

        /**
         * bind() — mengikat data Movie ke View di dalam ViewHolder.
         * Dipanggil oleh onBindViewHolder() setiap kali ViewHolder di-recycle.
         */
        fun bind(movie: Movie) {
            binding.apply {
                tvTitle.text  = movie.title       // Set teks judul
                tvGenre.text  = movie.genre       // Set teks genre
                ratingBar.rating = movie.rating   // Set nilai bintang rating

                // Click listener di-set pada root CardView
                // Saat diklik, kirim objek 'movie' ke Activity via lambda
                cardMovie.setOnClickListener {
                    onItemClick(movie)
                }
            }
        }
    }

    // =========================================================================
    // onCreateViewHolder — dipanggil saat RecyclerView membutuhkan ViewHolder BARU
    //                      (pool kosong atau pertama kali dirender).
    // =========================================================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        // Inflate layout item_movie.xml menggunakan ViewBinding
        // attachToParent = false karena RecyclerView yang mengelola attachment
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    // =========================================================================
    // onBindViewHolder — dipanggil untuk mengikat data ke ViewHolder yang ada.
    // getItem(position) adalah method ListAdapter yang thread-safe.
    // =========================================================================
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
