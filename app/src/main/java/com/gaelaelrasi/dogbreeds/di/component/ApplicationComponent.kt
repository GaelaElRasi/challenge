package com.gaelaelrasi.dogbreeds.di.component

import com.gaelaelrasi.dogbreeds.data.remote.BreedsRepo
import com.gaelaelrasi.dogbreeds.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component ( modules = [ NetworkModule::class ] )
interface ApplicationComponent {

    fun getBreeds(): BreedsRepo

}