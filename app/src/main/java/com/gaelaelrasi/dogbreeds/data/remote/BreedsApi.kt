package com.gaelaelrasi.dogbreeds.data.remote

import com.gaelaelrasi.dogbreeds.data.model.Breed
import com.gaelaelrasi.dogbreeds.data.model.Image
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
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
    ):  Observable<List<Breed>>

    //Return 1 image matching the id passed.
    @GET("breeds/{image_id}")
    fun getBreedsImageById(
        @Header("api_key") api_key: Int,
        @Path("image_id") image_id: String
    ):  Observable<List<Image>>
}