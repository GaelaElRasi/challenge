package com.gaelaelrasi.dogbreeds.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gaelaelrasi.dogbreeds.activities.MainViewModel
import com.gaelaelrasi.dogbreeds.di.module.NetworkModule
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private var networkModule: NetworkModule) {

   /* override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(networkModule) as T
        }
        throw IllegalArgumentException("ViewModel not found")
    }*/
}