package com.gaelaelrasi.dogbreeds.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaelaelrasi.dogbreeds.DefaultApplication
import com.gaelaelrasi.dogbreeds.R
import com.gaelaelrasi.dogbreeds.data.model.Breed
import com.gaelaelrasi.dogbreeds.di.module.NetworkModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel: ViewModel() {

    private val listLiveData = MutableLiveData<List<Breed>>()
    val coinListLiveData : LiveData<List<Breed>> = listLiveData

    init {
        viewModelScope.launch {
            val repo = DefaultApplication().getApplicationComponent().getBreeds().getBreeds()
            listLiveData.value = repo
        }
    }
}