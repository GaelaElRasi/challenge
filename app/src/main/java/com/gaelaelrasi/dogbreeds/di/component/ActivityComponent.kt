package com.gaelaelrasi.dogbreeds.di.component

import com.gaelaelrasi.dogbreeds.ui.activities.MainActivity
import com.gaelaelrasi.dogbreeds.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [ NetworkModule::class] )
interface ActivityComponent {

    fun inject (mainActivity: MainActivity)

}