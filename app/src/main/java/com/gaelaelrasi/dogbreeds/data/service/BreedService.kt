package com.gaelaelrasi.dogbreeds.data.service

import com.gaelaelrasi.dogbreeds.data.model.Breed
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface BreedService {

    fun getBreeds(): Observable<List<Breed>>

    fun getBreedsByName(breed_name: String): Single<Breed>
}