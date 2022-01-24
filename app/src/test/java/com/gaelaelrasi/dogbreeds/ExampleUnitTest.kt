package com.gaelaelrasi.dogbreeds

import com.gaelaelrasi.dogbreeds.di.component.DaggerApplicationComponent
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun apiResponse_success() {
        val appComponent = DaggerApplicationComponent.create()
        runBlocking {
            //val breeds = appComponent.getBreeds().getBreeds()
            //println("Breeds: $breeds")
        }
    }
}