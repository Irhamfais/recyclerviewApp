package com.example.app.model

/**
 * Data class Movie — merepresentasikan satu item dalam daftar film.
 *
 * Menggunakan 'data class' agar Kotlin otomatis men-generate:
 *   - equals() & hashCode()  → wajib agar DiffUtil dapat membandingkan objek
 *   - copy()                 → berguna saat mengubah sebagian field (misal toggle favorite)
 *   - toString()             → memudahkan debugging di Logcat
 */
data class Movie(
    val id: Int,            // ID unik — identifier utama yang digunakan DiffUtil
    val title: String,      // Judul film yang ditampilkan di RecyclerView
    val genre: String,      // Genre atau kategori film
    val rating: Float,      // Rating bintang dalam skala 0.0 – 5.0
    val posterUrl: String   // URL gambar poster (untuk image loading library)
)
