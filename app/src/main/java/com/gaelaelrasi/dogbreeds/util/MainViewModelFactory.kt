package com.gaelaelrasi.dogbreeds.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gaelaelrasi.dogbreeds.ui.fragments.FragmentsViewModel
import com.gaelaelrasi.dogbreeds.di.module.NetworkModule
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private var breedsService: NetworkModule
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FragmentsViewModel::class.java)) {
            return FragmentsViewModel(breedsService) as T
        }
        throw IllegalArgumentException("ViewModel not found")
    }
}