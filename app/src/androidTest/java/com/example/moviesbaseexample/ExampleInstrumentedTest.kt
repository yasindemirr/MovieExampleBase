package com.example.moviesbaseexample

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.moviesbaseexample", appContext.packageName)
    }
}
class FormatSocialStatusUseCase {
    fun execute(followerCount: Long): String {
        if (followerCount<1_000_000) return followerCount.toString()

        val suffix= when{
            followerCount>=1000->"M"
            else->"K"
        }
        return when(followerCount){
            in 1_000 until 999_999-> String.format("%.1f%s",followerCount/1000, suffix)
            else->""
        }

    }
}