package com.gaelaelrasi.dogbreeds.data.remote

import com.gaelaelrasi.dogbreeds.data.model.Breed
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BreedsApi {

    @GET("breeds")
    fun getBreeds(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Observable<List<Breed>>

    @GET("breeds/search")
    fun getBreedsByName(
        @Header("api_key") api_key: Int,
        @Query("q") breed_name: String
    ): Single<Breed>
}