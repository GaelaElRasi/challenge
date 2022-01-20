package com.gaelaelrasi.dogbreeds.data.service

import com.gaelaelrasi.dogbreeds.data.model.Breed
import io.reactivex.rxjava3.core.Single

interface BreedService {

    fun getBreeds(): Single<Breed>
}