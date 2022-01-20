package com.gaelaelrasi.dogbreeds.data.remote

import com.gaelaelrasi.dogbreeds.R
import com.gaelaelrasi.dogbreeds.data.model.Breed
import javax.inject.Inject

class BreedsRepo @Inject constructor(private val breedsApi: BreedsApi) {

    fun getBreeds(): List<Breed>? {
        val base = breedsApi.getBreeds(R.string.api_key).execute().body()
        val breed = base?.let {
            listOf<Breed>()
        }

        return breed
    }
}