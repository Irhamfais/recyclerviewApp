package com.example.app

import org.junit.Test
import org.junit.Assert.*

/**
 * Unit test — berjalan di JVM lokal tanpa emulator.
 * Cocok untuk pengujian logika bisnis yang tidak bergantung pada Android framework.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        // Contoh unit test dasar — verifikasi operasi aritmetika
        assertEquals(4, 2 + 2)
    }
}
