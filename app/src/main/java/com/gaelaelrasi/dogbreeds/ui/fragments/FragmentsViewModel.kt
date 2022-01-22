package com.gaelaelrasi.dogbreeds.ui.fragments

import androidx.lifecycle.ViewModel
import com.gaelaelrasi.dogbreeds.R
import com.gaelaelrasi.dogbreeds.data.model.Breed
import com.gaelaelrasi.dogbreeds.data.model.Image
import com.gaelaelrasi.dogbreeds.data.service.BreedService
import com.gaelaelrasi.dogbreeds.di.module.NetworkModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FragmentsViewModel @Inject constructor(
    private val breedService: NetworkModule
    ): ViewModel() {

    /*Este comanda*/
    fun getBreeds(): Observable<List<Breed>> {
        return breedService.getBreedsService().getBreeds(30,0)
            .subscribeOn((Schedulers.io()))
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getBreedsByName(breed_name: String): Observable<List<Breed>>{
        return breedService.getBreedsService().getBreedsByName(R.string.api_key, breed_name)
            .subscribeOn((Schedulers.io()))
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getBreedsImageById(image_id: String): Observable<List<Image>>{
        return breedService.getBreedsService().getBreedsImageById(R.string.api_key, image_id)
            .subscribeOn((Schedulers.io()))
            .observeOn(AndroidSchedulers.mainThread())
    }
}