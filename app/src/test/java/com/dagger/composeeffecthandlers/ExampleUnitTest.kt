package com.dagger.composeeffecthandlers

import kotlinx.coroutines.delay

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    suspend fun add(a: Float, b: Float): Float {
        delay(1000)
        return a + b
    }
}