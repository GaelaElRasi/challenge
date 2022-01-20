package com.gaelaelrasi.dogbreeds.data.model

import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("temperament")
    var temperament: String? = null,

    @SerializedName("origin")
    var origin: String? = null,

    @SerializedName("breed_group")
    var breed_group: String? = null,
)
