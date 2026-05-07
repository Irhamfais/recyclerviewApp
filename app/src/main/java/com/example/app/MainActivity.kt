package com.example.app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.adapter.MovieAdapter
import com.example.app.databinding.ActivityMainBinding
import com.example.app.model.Movie

/**
 * MainActivity — entry point aplikasi.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val movieAdapter = MovieAdapter { movie ->
        Toast.makeText(this, "🎬 ${movie.title}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadMovies()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
            addItemDecoration(
                DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            )
        }
    }

    private fun loadMovies() {
        // CATATAN: Pastikan menggunakan URL langsung ke FILE GAMBAR (akhiran .jpg, .png, dsb)
        // Link yang sebelumnya adalah link ke HALAMAN WEB, sehingga gambar tidak muncul.
        val movies = listOf(
            Movie(1, "Inception",               "Sci-Fi / Thriller",  4.8f, "https://c8.alamy.com/comp/DBW2R3/inception-2010-leonardo-dicaprio-christopher-nolan-dir-016-moviestore-DBW2R3.jpg"),
            Movie(2, "Interstellar",             "Sci-Fi / Drama",     4.7f, "https://mir-s3-cdn-cf.behance.net/project_modules/hd/297acd129204217.616629e21fe76.png"),
            Movie(3, "The Dark Knight",          "Action / Crime",     5.0f, "https://m.media-amazon.com/images/S/pv-target-images/e9a43e647b2ca70e75a3c0af046c4dfdcd712380889779cbdc2c57d94ab63902.jpg"),
            Movie(4, "Parasite",                 "Drama / Thriller",   4.6f, "https://image.tmdb.org/t/p/original/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg"),
            Movie(5, "Everything Everywhere",    "Sci-Fi / Comedy",    4.5f, "https://m.media-amazon.com/images/S/pv-target-images/7bda4a09918bc18ae9b28da1e857b18c7798afdf77a550f6a31b166930d98c0b.jpg"),
            Movie(6, "Oppenheimer",              "Biography / Drama",  4.7f, "https://static1.srcdn.com/wordpress/wp-content/uploads/2023/05/oppenheimer-poster.jpg"),
            Movie(7, "Dune: Part Two",           "Sci-Fi / Adventure", 4.4f, "https://static1.srcdn.com/wordpress/wp-content/uploads/2024/01/dune-part-2-poster-showing-timothee-chalamet-as-paul-atreides-and-zendaya-as-chani-holding-daggers.jpeg"),
            Movie(8, "Whiplash",                 "Drama / Music",      4.9f, "https://wallpapercave.com/wp/wp1952004.jpg")
        )

        movieAdapter.submitList(movies)
    }
}
