package com.gaelaelrasi.dogbreeds.data.service

import com.gaelaelrasi.dogbreeds.data.model.Breed
import com.gaelaelrasi.dogbreeds.data.model.Image
import io.reactivex.rxjava3.core.Observable

interface BreedService {

    //Return all dog breeds
    fun getBreeds(pageNumber: Int): Observable<List<Breed>>

    //Return all dog breeds matching the breed name passed
    fun getBreedsByName(breed_name: String): Observable<List<Breed>>

    //Return one image matching the id passed.
    fun getBreedsImageById(image_id: String): Observable<List<Image>>
}