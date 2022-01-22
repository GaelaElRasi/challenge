package com.gaelaelrasi.dogbreeds.data.service

import com.gaelaelrasi.dogbreeds.R
import com.gaelaelrasi.dogbreeds.data.model.Breed
import com.gaelaelrasi.dogbreeds.data.model.Image
import com.gaelaelrasi.dogbreeds.data.remote.BreedsApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class BreedsServiceImpl @Inject constructor(private val breedsApi: BreedsApi): BreedService {

    override fun getBreeds(): Observable<List<Breed>> {
        return breedsApi.getBreeds(30,0)
    }

    override fun getBreedsByName(breed_name: String):  Observable<List<Breed>> {
        return breedsApi.getBreedsByName(R.string.api_key,breed_name)
    }

    override fun getBreedsImageById(image_id: String): Observable<List<Image>> {
        return breedsApi.getBreedsImageById(R.string.api_key, image_id)
    }
}