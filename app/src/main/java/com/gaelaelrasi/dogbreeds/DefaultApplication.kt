package com.gaelaelrasi.dogbreeds

import android.app.Application
import com.gaelaelrasi.dogbreeds.di.component.ApplicationComponent
import com.gaelaelrasi.dogbreeds.di.component.DaggerApplicationComponent

class DefaultApplication: Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder().build()
    }

    fun getApplicationComponent(): ApplicationComponent{
        return appComponent
    }
}