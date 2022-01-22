package com.gaelaelrasi.dogbreeds.data.service

import com.gaelaelrasi.dogbreeds.data.model.Breed
import com.gaelaelrasi.dogbreeds.data.model.Image
import io.reactivex.rxjava3.core.Observable

interface BreedService {

    fun getBreeds(): Observable<List<Breed>>

    fun getBreedsByName(breed_name: String): Observable<List<Breed>>

    fun getBreedsImageById(image_id: String): Observable<List<Image>>
}