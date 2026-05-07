package com.example.app.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.app.model.Movie

/**
 * MovieDiffCallback — utilitas perbandingan untuk ListAdapter.
 *
 * DiffUtil membandingkan list LAMA vs BARU di background thread,
 * lalu menghasilkan operasi minimal (insert/remove/move/change)
 * untuk dianimasikan di RecyclerView.
 */
class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {

    /**
     * Apakah dua item merepresentasikan OBJEK YANG SAMA?
     * Gunakan ID unik — bukan konten — untuk identifikasi posisi item.
     * Jika true, DiffUtil akan melanjutkan ke areContentsTheSame().
     */
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * Apakah KONTEN item sama persis?
     * Dipanggil hanya jika areItemsTheSame() == true.
     * Karena Movie adalah data class, equals() otomatis membandingkan semua field.
     * Jika false → RecyclerView akan memperbarui tampilan item tersebut.
     */
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
