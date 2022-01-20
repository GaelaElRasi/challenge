package com.gaelaelrasi.dogbreeds.di.component

import com.gaelaelrasi.dogbreeds.activities.MainActivity
import com.gaelaelrasi.dogbreeds.activities.MainViewModel
import com.gaelaelrasi.dogbreeds.di.module.NetworkModule
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

@Component (modules = [ NetworkModule::class ] )
interface ActivityComponent {

    fun inject (mainViewModel: MainViewModel)

}