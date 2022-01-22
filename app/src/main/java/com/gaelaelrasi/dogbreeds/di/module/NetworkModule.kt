package com.gaelaelrasi.dogbreeds.di.module

import com.gaelaelrasi.dogbreeds.data.remote.BreedsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val API_BASE_URL = "https://api.thedogapi.com/v1/"
const val API_KEY = "6cadd723-60ce-4d06-82c1-5c81ddb3f873"

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun getRetroFitInstance(): Retrofit {

        val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.addHeader("x-api-key", API_KEY)
            chain.proceed(builder.build())
        }.build()

        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(httpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getBreedsService(): BreedsApi{
        return getRetroFitInstance().create(BreedsApi::class.java)
    }
}
