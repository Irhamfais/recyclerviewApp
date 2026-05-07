package com.example.app

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test — berjalan di perangkat Android fisik atau emulator.
 * Digunakan untuk pengujian yang membutuhkan konteks Android (Context, UI, dll).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Verifikasi bahwa package name aplikasi sudah benar
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.app", appContext.packageName)
    }
}
