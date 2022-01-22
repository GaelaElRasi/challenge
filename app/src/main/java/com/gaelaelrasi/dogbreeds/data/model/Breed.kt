package com.gaelaelrasi.dogbreeds.data.model

import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("weight")
    var weight: Metrics = Metrics(),

    @SerializedName("height")
    var height: Metrics = Metrics(),

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("bred_for")
    var bredFor: String? = null,

    @SerializedName("breed_group")
    var breedGroup: String? = null,

    @SerializedName("life_span")
    var lifeSpan: String? = null,

    @SerializedName("temperament")
    var temperament: String? = null,

    @SerializedName("origin")
    var origin: String? = null,

    @SerializedName("reference_image_id")
    var referenceImageId: String? = null,

    @SerializedName("image")
    var image: Image = Image(),
)
