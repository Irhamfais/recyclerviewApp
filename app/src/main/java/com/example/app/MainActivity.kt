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
 *
 * Bertanggung jawab untuk:
 *   1. Menginisialisasi ViewBinding
 *   2. Mengonfigurasi RecyclerView (LayoutManager, Adapter, dekorasi)
 *   3. Menyediakan data dan mengirimnya ke Adapter via submitList()
 */
class MainActivity : AppCompatActivity() {

    // ViewBinding: di-generate otomatis dari activity_main.xml
    // 'lateinit' karena hanya bisa diinisialisasi setelah inflate
    private lateinit var binding: ActivityMainBinding

    /**
     * MovieAdapter dideklarasikan di level class (bukan di dalam onCreate)
     * agar bisa diakses dari method lain seperti observeData() saat pindah ke ViewModel.
     *
     * Lambda onItemClick dipassing langsung — bersih tanpa boilerplate interface.
     */
    private val movieAdapter = MovieAdapter { movie ->
        // Aksi saat item diklik — tampilkan judul film sebagai Toast
        Toast.makeText(this, "🎬 ${movie.title}", Toast.LENGTH_SHORT).show()

        // TODO: Ganti dengan navigasi ke DetailActivity
        // startActivity(Intent(this, DetailActivity::class.java).apply {
        //     putExtra("EXTRA_MOVIE_ID", movie.id)
        // })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout dan set sebagai content view menggunakan ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadMovies()
    }

    /**
     * setupRecyclerView — konfigurasi semua properti RecyclerView.
     * Dipisah ke method sendiri agar onCreate tetap bersih dan mudah dibaca.
     */
    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            // LinearLayoutManager: menampilkan item secara vertikal (berurutan ke bawah)
            // Ganti dengan GridLayoutManager(context, 2) untuk tampilan 2 kolom
            layoutManager = LinearLayoutManager(this@MainActivity)

            // OPTIMASI: Beritahu RecyclerView bahwa ukurannya tidak berubah
            // saat isi adapter berubah → skip kalkulasi ulang layout RecyclerView
            setHasFixedSize(true)

            // Pasang adapter ke RecyclerView
            adapter = movieAdapter

            // Tambahkan garis pemisah antar item (opsional)
            addItemDecoration(
                DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            )
        }
    }

    /**
     * loadMovies — menyediakan data dan mengirimkan ke adapter.
     *
     * Di aplikasi production, ganti dengan:
     *   viewModel.movies.observe(this) { movies ->
     *       movieAdapter.submitList(movies)
     *   }
     *
     * submitList() secara otomatis:
     *   - Menghitung perbedaan dengan list lama (via DiffUtil di background thread)
     *   - Menganimasikan perubahan (insert, remove, update item)
     *   - Tidak me-render ulang seluruh list jika tidak perlu
     */
    private fun loadMovies() {
        val movies = listOf(
            Movie(1, "Inception",               "Sci-Fi / Thriller",  4.8f, "https://www.imdb.com/title/tt1375666/"),
            Movie(2, "Interstellar",             "Sci-Fi / Drama",     4.7f, "https://en.wikipedia.org/wiki/Interstellar_%28film%29"),
            Movie(3, "The Dark Knight",          "Action / Crime",     5.0f, "https://id.wikipedia.org/wiki/The_Dark_Knight_%28film%29"),
            Movie(4, "Parasite",                 "Drama / Thriller",   4.6f, "https://www.imdb.com/title/tt6751668/"),
            Movie(5, "Everything Everywhere",    "Sci-Fi / Comedy",    4.5f, "https://tomorrowtheater.org/movies/everything-everywhere-all-at-once-film-series-curated-by-stephanie-hsu/"),
            Movie(6, "Oppenheimer",              "Biography / Drama",  4.7f, "https://id.wikipedia.org/wiki/Oppenheimer_%28film%29"),
            Movie(7, "Dune: Part Two",           "Sci-Fi / Adventure", 4.4f, "https://en.wikipedia.org/wiki/Dune:_Part_Two"),
            Movie(8, "Whiplash",                 "Drama / Music",      4.9f, "https://medium.com/incluvie/whiplash-review-a-relentless-strive-for-perfection-840da6e89571")
        )

        // submitList(): kirim data baru — ListAdapter menghitung diff secara otomatis
        movieAdapter.submitList(movies)
    }
}
