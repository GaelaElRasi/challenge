package com.gaelaelrasi.dogbreeds.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("categories")
    @Expose
    var categories: Category = Category(),

    @SerializedName("breeds")
    @Expose
    var breeds: Breed = Breed()
)
