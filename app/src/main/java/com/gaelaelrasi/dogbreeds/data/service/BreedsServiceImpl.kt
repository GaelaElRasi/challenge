package com.gaelaelrasi.dogbreeds.data.service

import com.gaelaelrasi.dogbreeds.data.model.Breed
import com.gaelaelrasi.dogbreeds.data.model.Image
import com.gaelaelrasi.dogbreeds.data.remote.BreedsApi
import com.gaelaelrasi.dogbreeds.util.Constants
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class BreedsServiceImpl @Inject constructor(private val breedsApi: BreedsApi): BreedService {

    override fun getBreeds(pageNumber: Int): Observable<List<Breed>> {
        return breedsApi.getBreeds(51,pageNumber)
    }

    override fun getBreedsByName(breed_name: String):  Observable<List<Breed>> {
        return breedsApi.getBreedsByName(Constants.API_KEY,breed_name)
    }

    override fun getBreedsImageById(image_id: String): Observable<List<Image>> {
        return breedsApi.getBreedsImageById(Constants.API_KEY, image_id)
    }
}